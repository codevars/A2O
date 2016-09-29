package com.codevars.a2o.LocalStorage;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {

    SharedPreferences pref;

    SharedPreferences.Editor editor;

    Context context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "A2O Pref";


    public SessionManagement(Context context) {

        this.context = context;

        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);

        editor = pref.edit();

    }

}
