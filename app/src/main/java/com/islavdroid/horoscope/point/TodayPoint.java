package com.islavdroid.horoscope.point;

import com.islavdroid.horoscope.model.Horoscope;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface TodayPoint {
    @GET("/horoscope/today/{sunsign}")
    Call<Horoscope> getTodayHoroscope(@Path("sunsign") String sunsign);

    @GET("/horoscope/week/{sunsign}")
    Call<Horoscope> getWeekHoroscope(@Path("sunsign") String sunsign);

    @GET("/horoscope/month/{sunsign}")
    Call<Horoscope> getMonthHoroscope(@Path("sunsign") String sunsign);

    @GET("/horoscope/year/{sunsign}")
    Call<Horoscope> getYearHoroscope(@Path("sunsign") String sunsign);
}

