package com.geowind.hunong.entity;

import com.geowind.hunong.annotation.Colnum;
import com.geowind.hunong.annotation.ID;
import com.geowind.hunong.annotation.TableName;
import com.geowind.hunong.dao.DBHelper;

/**
 * Created by zhangwen on 2016/11/22.
 */
@TableName(DBHelper.LIBSEARCH_TABLE_NAME)
public class LibSearch {
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String id;
    private String url;
    @Colnum(DBHelper.ARTICLE_TITLE)
    private String title;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    private String summary;

}
