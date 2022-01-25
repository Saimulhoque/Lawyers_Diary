package com.forbitbd.lawyersdiary.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreference {

    private static final String SP_NAME = "SSSPPP";
    private static final String LANGUAGE = "LANGUAGE";

    private static AppPreference appPreference = null;
    SharedPreferences sp;

    private AppPreference(Context context){
        sp = context.getSharedPreferences(SP_NAME, context.MODE_PRIVATE);
    }

    public static AppPreference getInstance(Context context){
        if (appPreference == null){
            appPreference = new AppPreference(context);
        }
        return appPreference;
    }

    public String getLanguage(){
        String language = sp.getString(LANGUAGE, "EN");
        return language;
    }

    public void setLanguage(String value){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(LANGUAGE,value);
        editor.apply();
    }

}
