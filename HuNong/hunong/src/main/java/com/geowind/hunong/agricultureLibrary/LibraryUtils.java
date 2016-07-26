package com.geowind.hunong.agricultureLibrary;

import com.geowind.hunong.entity.Library;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by logaxy on 2016/7/25.
 */
public class LibraryUtils {

    private ArrayList<Library> libraryArrayList;

    public ArrayList<Library> getLibraryArrayList() {
        return libraryArrayList;
    }

//    public String  getJsonString(String urlString) {
//        try {
//            URL httpUrl = new URL(urlString);
//            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            StringBuffer sb = new StringBuffer();
//            String line;
//            while(null != (line = reader.readLine())) {
//                sb.append(line);
//            }
//            return  sb.toString();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public void fromJson(String jsonString){
        Type listType = new TypeToken<LinkedList<Library>>(){}.getType();
        Gson gson=new Gson();
        LinkedList<Library> libraries=gson.fromJson(jsonString,listType);
        for(Iterator iterator = libraries.iterator(); iterator.hasNext(); ){
            Library library= (Library) iterator.next();
            libraryArrayList.add(library);
        }
    }
}
