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
}
