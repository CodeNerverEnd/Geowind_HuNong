package com.geowind.hunong.protocal;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

/**
 * Created by zhangwen on 16-7-19.
 */
public class Leaf {
    private  String tagName;
    private  String tagValue;
    public  Leaf(String tagName){
        this.tagName=tagName;
    }
    public Leaf(String tagName,String tagValue){
        this.tagName=tagName;
        this.tagValue=tagValue;
    }
    public void serializerLeaf( XmlSerializer serializer){

        try {
            serializer.startTag(null,tagName);
            serializer.text(tagValue);
            serializer.endTag(null,tagName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
