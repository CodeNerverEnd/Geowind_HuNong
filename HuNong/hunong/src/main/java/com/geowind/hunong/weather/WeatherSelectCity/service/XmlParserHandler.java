package com.geowind.hunong.weather.weatherselectcity.service;

import com.geowind.hunong.weather.weatherselectcity.model.CityModel;
import com.geowind.hunong.weather.weatherselectcity.model.DistrictModel;
import com.geowind.hunong.weather.weatherselectcity.model.ProvinceModel;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 * Created by logaxy on 16-11-23.
 */
public class XmlParserHandler extends DefaultHandler {
    private String nodeName = null;
    private List<ProvinceModel> provinceList = new ArrayList<ProvinceModel>();
    public XmlParserHandler() {
    }

    public List<ProvinceModel> getDataList() {
        return provinceList;
    }

    @Override
    public void startDocument() throws SAXException {
    }

    ProvinceModel provinceModel = new ProvinceModel();
    CityModel cityModel = new CityModel();
    DistrictModel districtModel = new DistrictModel();

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        nodeName = localName;
        if (localName.equals("pn")) {
            provinceModel = new ProvinceModel();
            provinceModel.setCityList(new ArrayList<CityModel>());
        } else if (localName.equals("cn")) {
            cityModel = new CityModel();
            cityModel.setDistrictList(new ArrayList<DistrictModel>());
        } else if (localName.equals("d")) {
            districtModel = new DistrictModel();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (localName.equals("d")) {
            if (districtModel.getName().length()<=0) return;
            cityModel.getDistrictList().add(districtModel);
        } else if (localName.equals("c")) {
            provinceModel.getCityList().add(cityModel);
        } else if (localName.equals("p")) {
            provinceList.add(provinceModel);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        StringBuilder sb = new StringBuilder();
        sb.append(ch, start, length);
        if(sb.length()==1||sb.length()==8||sb.length()==12) return;
        if (nodeName.equals("pn")) {
            provinceModel.setName(sb.toString());
        } else if (nodeName.equals("cn")) {
            cityModel.setName(sb.toString());
        } else if (nodeName.equals("d")) {
            districtModel.setName(sb.toString());
        }
    }
}

