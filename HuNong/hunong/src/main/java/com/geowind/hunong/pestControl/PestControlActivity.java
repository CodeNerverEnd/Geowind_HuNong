package com.geowind.hunong.pestControl;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.geowind.hunong.R;
import com.geowind.hunong.global.activitys.BaseActivity;
import com.geowind.hunong.utils.MyConstants;
import com.zfdang.multiple_images_selector.ImagesSelectorActivity;
import com.zfdang.multiple_images_selector.SelectorSettings;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by logaxy on 2016/9/22.
 * 病虫妨害，上传图片和描述
 */
public class PestControlActivity extends BaseActivity implements View.OnClickListener{

    private static final int REQUEST_CODE = 100;
    private int MAX_IMAGES=4;//最多选择的图片数量
    private Button confirm;//确认按钮

    private EditText editText;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView[] imageViews=new ImageView[4];

    private int currentImageView=0;//当前正在操作的ImageView的序号，主要作imageViews下标用，0开始

    private ArrayList<String> mResults = new ArrayList<>();//存放选图片路径的ArrayList

    /*
     * titlebar相关
     * */
    private TextView titil;
    private ImageButton returnButton;

    /**
     * 服务器地址
     */
    public static final String uploadUrl = MyConstants.PESTCONTROL_UPLOAD_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pest_control);

        /*
        * titlebar设置
        * */
        titil=(TextView) findViewById(R.id.title);
        returnButton= (ImageButton) findViewById(R.id.return_btn);
        titil.setText("病虫害反馈");
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //必须初始化Fresco，否则不能使用图片多选择器
        Fresco.initialize(getApplicationContext());

        confirm= (Button) findViewById(R.id.btn_comfirm);
        editText= (EditText) findViewById(R.id.editText);
        imageView1= (ImageView) findViewById(R.id.imageView1);
        imageView2= (ImageView) findViewById(R.id.imageView2);
        imageView3= (ImageView) findViewById(R.id.imageView3);
        imageView4= (ImageView) findViewById(R.id.imageView4);
        imageViews[0]=imageView1;
        imageViews[1]=imageView2;
        imageViews[2]=imageView3;
        imageViews[3]=imageView4;

        for(ImageView iv:imageViews){
            iv.setOnClickListener(this);
            iv.setClickable(false);
        }
        imageViews[currentImageView].setClickable(true);
        imageViews[currentImageView].setImageResource(R.drawable.add_pre);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PestControlActivity.this, "上传中...", Toast.LENGTH_SHORT).show();

                /****************************/
                //利用Handler判断是否上传成功
                final Handler handler=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        //上传成功
                        if(msg.what == 1)
                        {
                            Intent intent=new Intent(PestControlActivity.this,ResultActivity.class);
                            startActivity(intent);
                        }
                        //上传不成功
                        else
                        {
                            Intent intent=new Intent(PestControlActivity.this,FailedToUpload.class);
                            startActivity(intent);
                        }
                    }
                };
                /****************************/

                // TODO Auto-generated method stub
                new Thread() {
                    public void run() {
                        String result="0";//服务器返回结果

                        ArrayList<File> fileArrayList=new ArrayList<File>();
                        for (String s:mResults) {
                            File file=new File(s);
                            fileArrayList.add(file);//待上传文件列表
                        }

                        final Map<String, String> map = new HashMap<String, String>();
                        map.put("op", "askInsertInfo");
                        map.put("username", "geowind");//id
                        map.put("describe", ""+editText.getText().toString());//文本框文本
                        try {
                            result = multiImagesUploadUtil.uploadSubmit(uploadUrl, map, fileArrayList);
                            System.out.println("服务器返回的结果，成功为1，否则为0:" + result);

                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        //把“1”或“0”以int形式传回个给主线程，以便后续界面处理
                        Message msg = handler.obtainMessage(Integer.parseInt(result));
                        msg.sendToTarget();
                    }
                }.start();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                //获得图片路径
                mResults = data.getStringArrayListExtra(SelectorSettings.SELECTOR_RESULTS);
                assert mResults != null;

                if(mResults.size()>0 && mResults!=null){

                    //imageViews下标从0开始，currentImageView = [mResults.size()-1] + 1
                    currentImageView=mResults.size();

                    //设置全部imageView不可点击
                    for(ImageView iv :imageViews){
                        Bitmap bitmap=null;
                        iv.setImageBitmap(bitmap);
                        iv.setClickable(false);
                    }

                    /**
                     * 利用选择的图片时得到的路径，构造bitmap
                     * 依次转化为正方形图片后设置到imageView中
                     */
                    for(int i=0,len=mResults.size();i<len;i++){
                        Bitmap bitmap= BitmapFactory.decodeFile(mResults.get(i));
                        int width=Math.min(bitmap.getWidth(),bitmap.getHeight())/2;
                        imageViews[i].setImageBitmap(MyPhotoUtil.changeBitmapToSquare(bitmap, width));
                    }
                }

                //设置当前imageView图片为“选择”图片，并设置为可以点击
                if(currentImageView<MAX_IMAGES) {
                    imageViews[currentImageView].setClickable(true);
                    imageViews[currentImageView].setImageResource(R.drawable.add);
                }

                //即使已经选择了最后一张图片，最后一个imageView也应该可以点击
                if(currentImageView==MAX_IMAGES)
                    imageViews[currentImageView-1].setClickable(true);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //跳转并选择多张图片
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(PestControlActivity.this, ImagesSelectorActivity.class);
        intent.putExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER, MAX_IMAGES);
        intent.putExtra(SelectorSettings.SELECTOR_MIN_IMAGE_SIZE, 8000);//过滤过小的图片
        intent.putExtra(SelectorSettings.SELECTOR_SHOW_CAMERA, false);//false即不要拍照框
        intent.putStringArrayListExtra(SelectorSettings.SELECTOR_INITIAL_SELECTED_LIST, mResults);
        startActivityForResult(intent, REQUEST_CODE);
    }
}



