package com.islavdroid.horoscope.model;

import com.google.gson.annotations.SerializedName;


public class Horoscope {
    @SerializedName("date")
    String date;
    @SerializedName("horoscope")
    String horoscope;

    @SerializedName("sunsign")
    String sunsign;

    @SerializedName("week")
    String week;
    @SerializedName("month")
    String month;
    @SerializedName("year")
    String year;

    public String getWeek() {
        return week;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getSunsign() {
        return sunsign;
    }

    public String getDate() {
        return date;
    }

    public String getHoroscope() {
        return horoscope;
    }
}
