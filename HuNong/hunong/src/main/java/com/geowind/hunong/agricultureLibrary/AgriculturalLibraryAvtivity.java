package com.geowind.hunong.agricultureLibrary;


import android.app.ListActivity;
import android.os.Bundle;

import com.geowind.hunong.entity.Library;
import com.geowind.hunong.utils.MyConstants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class AgriculturalLibraryAvtivity extends ListActivity {

    ArrayList<Library> libraryArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private ArrayList<Library> requstLibrary(int category, int begin) {


        int mCategory=category;
        int mBegin=begin;
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams params =new RequestParams();
        params.add("method","getTitles");
        params.add("category",String.valueOf(category));
        params.add("begin",String.valueOf(begin));
        client.post(MyConstants.LibraryURL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                LibraryUtils libraryUtils=new LibraryUtils();
                String jsonString=new String(responseBody);
                libraryUtils.fromJson(jsonString);
                libraryArrayList=libraryUtils.getLibraryArrayList();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

        return libraryArrayList;
    }
}
