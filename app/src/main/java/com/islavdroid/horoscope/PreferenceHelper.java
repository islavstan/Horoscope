package com.islavdroid.horoscope;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {
    //класс для сохранения настроек
    public static final String NOTIFICATION ="notification";
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
}


