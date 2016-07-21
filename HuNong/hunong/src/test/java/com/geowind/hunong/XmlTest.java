package com.geowind.hunong;

import android.test.AndroidTestCase;
import android.util.Xml;

import com.geowind.hunong.utils.MyConstants;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by zhangwen on 16-7-19.
 */
public class XmlTest extends AndroidTestCase {
    public void testCreateXml(){
        XmlSerializer xmlSerializer= Xml.newSerializer();
        StringWriter writer=new StringWriter();
        try {
            xmlSerializer.setOutput(writer);
            xmlSerializer.startDocument(MyConstants.ENCODING,null);
            xmlSerializer.startTag(null,"massage");
            xmlSerializer.startTag(null,"header");
            xmlSerializer.startTag(null,"userId");
            xmlSerializer.endDocument();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
