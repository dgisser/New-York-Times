package com.facebook.dgisser.new_york_times.Models;

import java.io.Serializable;

/**
 * Created by dgisser on 6/19/16.
 */
public class Article implements Serializable {
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

    public Article(String webUrl, String headline, String url) {
        this.webUrl = webUrl;
        this.headline = headline;
        this.thumbNail = url;
    }
}
