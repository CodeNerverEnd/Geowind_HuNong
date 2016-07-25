package com.geowind.hunong.entity;

/**
 * Created by Kui on 2016/7/23.
 */
public class Library {
    //标题
    private String title;
    //关键字
    private String keyword;
    //url
    private String url;
    //简述
    private String headContent;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Library{" +
                "title='" + title + '\'' +
                ", keyword='" + keyword + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
