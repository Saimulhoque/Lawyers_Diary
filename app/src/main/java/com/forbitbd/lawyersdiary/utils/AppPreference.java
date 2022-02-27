package com.forbitbd.lawyersdiary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.forbitbd.lawyersdiary.model.Lawyer;

public class AppPreference {

    private static final String SP_NAME="USER_PREF";

    private static final String ID="ID";
    private static final String NAME="NAME";
    private static final String EMAIL="EMAIL";
    private static final String CONTACT="CONTACT";
    private static final String IMAGE="IMAGE";
    private static final String ADDRESS="ADDRESS";

    private static AppPreference appPreference = null;

    SharedPreferences sp;

    private AppPreference(Context context) {
        sp = context.getSharedPreferences(SP_NAME,context.MODE_PRIVATE);
    }

    public static AppPreference getInstance(Context context){
        if(appPreference==null){
            appPreference = new AppPreference(context);
        }
        return appPreference;
    }

    public void setLawyer(Lawyer lawyer){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(ID,lawyer.get_id());
        editor.putString(NAME,lawyer.getName());
        editor.putString(EMAIL,lawyer.getEmail());
        editor.putString(CONTACT,lawyer.getMobile());
        editor.putString(IMAGE,lawyer.getImage());
        editor.putString(ADDRESS,lawyer.getAddress());
        editor.apply();
    }

    public Lawyer getLawyer(){
        Lawyer lawyer = new Lawyer();
        lawyer.set_id(sp.getString(ID,null));
        lawyer.setName(sp.getString(NAME,null));
        lawyer.setEmail(sp.getString(EMAIL,null));
        lawyer.setMobile(sp.getString(CONTACT,null));
        lawyer.setImage(sp.getString(IMAGE,null));
        lawyer.setAddress(sp.getString(ADDRESS,null));
        return lawyer;
    }

    public void clear(){
        sp.edit().clear().apply();
    }
}
