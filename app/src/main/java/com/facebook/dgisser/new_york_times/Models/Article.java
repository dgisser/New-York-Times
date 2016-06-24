package com.facebook.dgisser.new_york_times.Models;


import org.parceler.Parcel;


/**
 * Created by dgisser on 6/19/16.
 */
@Parcel
public class Article{
    String webUrl;

    public String getWebUrl() {
        return webUrl;
    }

    public String getHeadline() {
        return headline;
    }

    public String getThumbNail() {
        return thumbNail;
    }

    String headline;
    String thumbNail;

    public Article() {}

    public Article(String webUrl, String headline, String url) {
        this.webUrl = webUrl;
        this.headline = headline;
        this.thumbNail = url;
    }
}
