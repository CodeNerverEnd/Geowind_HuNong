package com.geowind.hunong.pestControl;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.geowind.hunong.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class pestControlActivity extends Activity {

        private static final int NONE = 0;

        private static final int PHOTO_ZOOM = 2; // 缩放
        private static final int PHOTO_GRAPH = 1;// 拍照+
        private static final int PHOTO_RESOULT = 3;// 结果
        private static final String IMAGE_UNSPECIFIED = "image/*";
        private ImageView imageView;
        private Button openAlbum;
        private Button openCamera;

    Date date;
    SimpleDateFormat formatter;
    String myFileName;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pest_control);

            imageView  = (ImageView) findViewById(R.id.ImageView);
            openAlbum  = (Button)    findViewById(R.id.btnOpenAlbum);
            openCamera = (Button)    findViewById(R.id.btnOpenCamera);

            openAlbum.setOnClickListener(onClickListener);
            openCamera.setOnClickListener(onClickListener);
        }

        private final View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(v==openAlbum){ //从相册获取图片
                    Intent intent = new Intent(Intent.ACTION_PICK, null);
                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UNSPECIFIED);
                    startActivityForResult(intent, PHOTO_ZOOM);
                }else if(v==openCamera){ //从拍照获取图片

                    formatter =new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
                    date = new Date(System.currentTimeMillis());
                    myFileName = "/HuNong/HN"+formatter.format(date)+".jpg";

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment
                            .getExternalStorageDirectory(),myFileName)));
                    startActivityForResult(intent, PHOTO_GRAPH);
                }

            }

        };

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (resultCode == NONE)
                return;
            // 拍照
            if (requestCode == PHOTO_GRAPH) {

//                File file = new File(Environment.getExternalStorageDirectory()+"/HuNongPictures/");
//
//            if(!file.exists()){
//                file.mkdirs();
//            }

                // 设置文件保存路径
                File picture = new File(Environment.getExternalStorageDirectory()
                        + myFileName);
                startPhotoZoom(Uri.fromFile(picture));
            }

            if (data == null)
                return;

            // 读取相册缩放图片
            if (requestCode == PHOTO_ZOOM) {
                startPhotoZoom(data.getData());
            }
            // 处理结果
            if (requestCode == PHOTO_RESOULT) {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Bitmap photo = extras.getParcelable("data");
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    photo.compress(Bitmap.CompressFormat.JPEG, 80, stream);//压缩

                    if(photo!=null)
                        imageView.setImageBitmap(photo); //把图片显示在ImageView控件上


                }

            }

            super.onActivityResult(requestCode, resultCode, data);
        }

        /**
         * 收缩图片
         *
         * @param uri
         */
        public void startPhotoZoom(Uri uri) {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
            intent.putExtra("crop", "true");
            // aspectX aspectY 是宽高的比例
            intent.putExtra("aspectX", 2);
            intent.putExtra("aspectY", 3);
            // outputX outputY 是裁剪图片宽高
            intent.putExtra("outputX", 400);
            intent.putExtra("outputY", 600);
            intent.putExtra("return-data", true);
            startActivityForResult(intent, PHOTO_RESOULT);
        }




//    ImageView myImageView;
//    Button openAlbum;//打开相册
//    Button openCamera;//打开相机
//
//    Bitmap bitmap=null;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pest_control);
//
//        myImageView= (ImageView) findViewById(R.id.myImageView);
//        openAlbum= (Button) findViewById(R.id.openAlbum);
//        openCamera= (Button) findViewById(R.id.openCamera);
//
//        openAlbum.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(Intent.ACTION_PICK,
//                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent,1);
//            }
//        });
//
//
//        openCamera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, 2);
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        /**
//         * 从相册中选择图片
//         */
//        if(requestCode==1&&resultCode == Activity.RESULT_OK && null != data){
//            Uri selectedImage = data.getData();
//            String[] filePathColumns={MediaStore.Images.Media.DATA};
//            Cursor c = this.getContentResolver().query(selectedImage, filePathColumns, null,null, null);
//            c.moveToFirst();
//            int columnIndex = c.getColumnIndex(filePathColumns[0]);
//            String picturePath= c.getString(columnIndex);
//            c.close();
//            bitmap= BitmapFactory.decodeFile(picturePath,null);//选中的图片
//        }
//
//        /**
//         * 打开相机拍摄图片
//         */
//        if(requestCode==2&&resultCode == Activity.RESULT_OK && null != data){
//
//            File file;
//
//            String sdStatus = Environment.getExternalStorageState();
//            if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
//                Toast.makeText(this,"手机储存异常！",Toast.LENGTH_SHORT);
//                return;
//            }
//
//            Bundle bundle = data.getExtras();
//            bitmap = (Bitmap) bundle.get("data");
//
//          file = new File("sdcard/HuNongPictures/");
//   //         file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath()+"/HuNongPictures/");
//
//            /**
//             * 如果/sdcard/HuNongPictures/不存在，则创建
//             */
//            if(!file.exists()){
//                file.mkdirs();
//            }
//
//            /**
//             * 获得系统时间，并以其命名图片
//             */
//            SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
//            Date date = new Date(System.currentTimeMillis());
//
//            String fileName = file.getPath()+"/HN"+formatter.format(date)+".jpg";
//            FileOutputStream fos=null;
//            try {
//                fos = new FileOutputStream(fileName);
//                bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            finally {
//                try {
//                    fos.flush();  //
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//
//        }
//
//
//        /**
//         * 显示图片
//         */
//        if(bitmap!=null)
//            myImageView.setImageBitmap(bitmap);
//    }

    }

