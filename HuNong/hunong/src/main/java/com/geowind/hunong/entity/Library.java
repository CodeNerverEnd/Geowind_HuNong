package com.geowind.hunong.entity;

/**
 * Created by Kui on 2016/7/23.
 */
public class Library {
    //编号
    private int id;
    //类别
    private String category;
    //标题
    private String title;
    //url
    private String url;
    //头部
    private String headHead;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeadHead() {
        return headHead;
    }

    public void setHeadHead(String headHead) {
        this.headHead = headHead;
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", headHead='" + headHead + '\'' +
                '}';
    }
}
