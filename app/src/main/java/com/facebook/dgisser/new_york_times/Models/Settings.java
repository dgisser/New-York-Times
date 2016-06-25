package com.facebook.dgisser.new_york_times.Models;

import org.parceler.Parcel;

/**
 * Created by dgisser on 6/23/16.
 */

@Parcel
public class Settings {

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    int flags;
    int pos;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String date;

    public Settings(){

    }

    public Settings(int flags, int pos, String date) {
        this.flags = flags;
        this.pos = pos;
        this.date = date;
    }

}
