package com.geowind.hunong.entity;

/**
 * Created by ${zhangwen} on 2016/10/27.
 */

public class BBs {
  private String title;
  private String resource;
    private String img;
    private String date;
    private String _author;
    private String agreeCount;

    public String getTitle() {
        return title;
    }

    public String getResource() {
        return resource;
    }

    public String getImg() {
        return img;
    }

    public String getDate() {
        return date;
    }

    public String get_author() {
        return _author;
    }

    public String getAgreeCount() {
        return agreeCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    private String commentCount;
}
