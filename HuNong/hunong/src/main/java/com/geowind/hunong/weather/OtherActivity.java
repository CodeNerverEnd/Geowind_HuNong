package com.geowind.hunong.weather;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.geowind.hunong.R;
import com.geowind.hunong.agricultureLibrary.LibraryUtils;
import com.geowind.hunong.entity.Library;

import java.util.ArrayList;
import java.util.List;


public class OtherActivity extends Activity {

    List<Library> libraryArrayList = new ArrayList<>();
    private Button btn;
    private TextView tv;
    private final int MSG = 1;
    String s="111111111111";
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG:
                    tv.setText((String)msg.obj);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);



        btn = (Button) findViewById(R.id.connect);
        tv= (TextView) findViewById(R.id.tv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LibraryUtils.requstLibrary(1, 0);
                libraryArrayList = LibraryUtils.libraryArrayList;

//                Library library = libraryArrayList.get(0);

//                Message message = new Message();
//                message.what = MSG;
//                message.obj = library.toString();
//                handler.sendMessage(message);
                //Library library = libraryArrayList.get(0);
               //System.out.println(library.toString());
//                System.out.println(libraryArrayList.get(0).toString());

            }
        });

        System.out.println(libraryArrayList.size()+ "   dfsfsdfsdfdsfsdfs");
    }
}
