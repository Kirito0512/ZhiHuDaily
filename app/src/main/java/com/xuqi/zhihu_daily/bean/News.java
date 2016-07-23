package com.xuqi.zhihu_daily.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/20.
 */
public class News implements Serializable{

    private String images;
    private int type;
    private int id;
    private String ga_prefix;
    private String title;

    public News(){

    }

    public News(int id,String title,String images){
        this.id = id;
        this.title = title;
        this.images = images;
    }
    public int getNews_id() {
        return id;
    }

    public void setNews_id(int news_id) {
        id = news_id;
    }

    public String getNews_title() {
        return title;
    }

    public void setNews_title(String news_title) {
        title = news_title;
    }

    public String getNews_image() {
        return images;
    }

    public void setNews_image(String news_image) {
        images = news_image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }
}
