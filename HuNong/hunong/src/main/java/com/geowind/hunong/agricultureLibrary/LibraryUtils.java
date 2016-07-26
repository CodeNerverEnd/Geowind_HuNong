package com.geowind.hunong.agricultureLibrary;

import com.geowind.hunong.entity.Library;
import com.geowind.hunong.utils.MyConstants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by logaxy on 2016/7/25.
 */
public class LibraryUtils {

    //总的List
    public static List<Library> libraryArrayList;
    static {
        libraryArrayList = new ArrayList<>();
    }

    //解析JSON
    public static List<Library> fromJson(String jsonString) {
        //临时List
        List<Library> interimLbraryList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                int category = jsonObject.getInt("category");
                String title = jsonObject.getString("title");
                String url = jsonObject.getString("url");
                String headContent = jsonObject.getString("headContent");
                Library library = new Library();
                library.setId(id);
                library.setCategory(category);
                library.setTitle(title);
                library.setUrl(url);
                library.setHeadContent(headContent);
                interimLbraryList.add(library);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return interimLbraryList;

    }

    /**
     *
     * @param category 类别
     * @param begin  起点
     */
    public static void requstLibrary(int category, int begin) {

        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams params =new RequestParams();
        params.add("method","getTitles");
        params.add("category",String.valueOf(category));
        params.add("begin",String.valueOf(begin));
        client.post(MyConstants.LibraryURL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String jsonString=new String(responseBody);
                System.out.println(jsonString);
                libraryArrayList.addAll(fromJson(jsonString));
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {


            }
        });
    }



}
