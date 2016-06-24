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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    int year;
    int month;
    int day;

    public Settings(){

    }

    public Settings(int flags, int pos, int year, int month, int day) {
        this.flags = flags;
        this.pos = pos;
        this.year = year;
        this.month = month;
        this.day = day;
    }

}
