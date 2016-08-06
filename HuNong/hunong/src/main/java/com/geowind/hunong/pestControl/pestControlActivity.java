package com.geowind.hunong.pestControl;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.geowind.hunong.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class pestControlActivity extends Activity {

    ImageView myImageView;
    Button openAlbum;
    Button openCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pest_control);

        myImageView= (ImageView) findViewById(R.id.myImageView);
        openAlbum= (Button) findViewById(R.id.openAlbum);
        openCamera= (Button) findViewById(R.id.openCamera);

        openAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1);
            }
        });


        openCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1&&resultCode == Activity.RESULT_OK && null != data){
            Uri selectedImage = data.getData();
            String[] filePathColumns={MediaStore.Images.Media.DATA};
            Cursor c = this.getContentResolver().query(selectedImage, filePathColumns, null,null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String picturePath= c.getString(columnIndex);
            c.close();
            Bitmap bitmap= BitmapFactory.decodeFile(picturePath,null);
            myImageView.setImageBitmap(bitmap);
        }

        if(requestCode==2&&resultCode == Activity.RESULT_OK && null != data){
            File file;

            String sdStatus = Environment.getExternalStorageState();
            if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
                Toast.makeText(this,"手机储存异常！",Toast.LENGTH_SHORT);
                return;
            }

            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");

            file = new File("/sdcard/HuNongPictures/");

            /**
             * 如果/sdcard/HuNongPictures/不存在，则创建
             */
            if(!file.exists()){
                file.mkdirs();
            }

            /**
             * 获得系统时间，并以其命名图片
             */
            SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());

            String fileName = file.getPath()+"/HN"+formatter.format(date)+".jpg";
            FileOutputStream fos=null;
            try {
                fos = new FileOutputStream(fileName);
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            /**
             * 显示图片
             */
            myImageView.setImageBitmap(bitmap);
        }
    }

}
