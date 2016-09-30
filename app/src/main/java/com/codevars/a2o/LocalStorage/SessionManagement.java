package com.codevars.a2o.LocalStorage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.codevars.a2o.Login;

public class SessionManagement {


    SharedPreferences pref;

    SharedPreferences.Editor editor;

    Context context;

    int PRIVATE_MODE = 0;


    private static final String PREF_NAME = "A2O Pref";

    public static final String LOGIN = "No";


    public void SessionManagement(Context context) {

        context = this.context;

        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);

        editor = pref.edit();

    }


    public boolean SplashDone() { return pref.getBoolean(LOGIN, false); }


    public void createsplashsession() {

        editor.putBoolean(LOGIN, true);

    }


    public void logincheck() {

        if (SplashDone()) {

            Intent go = new Intent(context, Login.class);

        }

    }


}
