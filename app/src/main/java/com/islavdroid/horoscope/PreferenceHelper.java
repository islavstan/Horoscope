package com.islavdroid.horoscope;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {
    //класс для сохранения настроек
    public static final String NOTIFICATION ="notification";
    public static final String TIME ="time";
    public static final String SUNSIGN ="sunsign";
    private static PreferenceHelper instance;
    private Context context;
    private SharedPreferences preferences;

    private PreferenceHelper() {
    }
    public static PreferenceHelper getInstance(){
        if(instance==null){
            instance =new PreferenceHelper();
        }
        return instance;
    }

    public void init(Context context){
        this.context =context;
        preferences = context.getSharedPreferences("preferences",Context.MODE_PRIVATE);

    }
    public void putBoolean(String key,boolean value){
        //сохраняем данные
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }
    public boolean getBoolean(String key){
        return preferences.getBoolean(key,false);

    }

    public void putString(String key,String value){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,value);
        editor.apply();
    }
    public String getString(String key){
        return preferences.getString(key,"");

    }
    public void putInt(String key,int value){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key,value);
        editor.apply();
    }
    public int getInt(String key){
        int a = preferences.getInt(key,0);
        return a;
    }

}


