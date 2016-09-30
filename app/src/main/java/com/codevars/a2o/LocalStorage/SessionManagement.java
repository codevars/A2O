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

    public static final String FIRST_TIME = "Yes";

    public static final String LOGIN = "No";


    public SessionManagement(Context context) {

        this.context = context;

        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);

        editor = pref.edit();

    }



    public boolean FirstTime() { return pref.getBoolean(FIRST_TIME, true); }

    public boolean IntroDone() { return pref.getBoolean(LOGIN, false); }



    public void unsetFirstTime(boolean state) {

        editor.putBoolean(FIRST_TIME, state);

        editor.commit();

    }


    public void createsplashsession() {

        editor.putBoolean(LOGIN, true);

        editor.commit();

    }


    public void logincheck() {

        if (this.IntroDone()) {

            Intent go = new Intent(context, Login.class);

            context.startActivity(go);

        }

    }


}
