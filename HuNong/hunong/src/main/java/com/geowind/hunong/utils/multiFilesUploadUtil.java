package com.geowind.hunong.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by logaxy on 2016/9/23.
 */

public class multiFilesUploadUtil {

    /**
     *
     * @param url 服务器地址
     * @param param
     * @param fileArrayList 待上传的文件List
     * @return 服务器返回的结果
     * @throws Exception
     */
    public static String uploadSubmit(String url, Map<String, String> param, ArrayList<File> fileArrayList) throws Exception {
        HttpPost post = new HttpPost(url);
        HttpClient httpClient=new DefaultHttpClient();
        MultipartEntity entity = new MultipartEntity();
        if (param != null && !param.isEmpty()) {
            for (Map.Entry<String, String> entry : param.entrySet()) {
                if (entry.getValue() != null && entry.getValue().trim().length() > 0) {
                    entity.addPart(entry.getKey(),new StringBody(entry.getValue(),
                            Charset.forName(org.apache.http.protocol.HTTP.UTF_8)));
                }
            }
        }
        //添加图片文件到entity对象中
        if(fileArrayList!=null&&fileArrayList.size()>0){
            for(int i=0,j=fileArrayList.size();i<j;i++){
                entity.addPart("images",new FileBody(fileArrayList.get(i)));
            }
        }
        else {
            System.out.println("服务器aaaaaaaaaaaaaaaa");
        }

        post.setEntity(entity);
        HttpResponse response = httpClient.execute(post);
        int stateCode = response.getStatusLine().getStatusCode();
        StringBuffer sb = new StringBuffer();
        if (stateCode == HttpStatus.SC_OK) {
            HttpEntity result = response.getEntity();
            if (result != null) {
                InputStream is = result.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String tempLine;
                while ((tempLine = br.readLine()) != null) {
                    sb.append(tempLine);
                }
            }
        }
        post.abort();
        return sb.toString();
    }
}
