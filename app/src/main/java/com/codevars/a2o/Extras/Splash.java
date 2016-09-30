package com.codevars.a2o.Extras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.codevars.a2o.Intro;
import com.codevars.a2o.LocalStorage.SessionManagement;
import com.codevars.a2o.Login;
import com.codevars.a2o.LoginRegisterTabbed;

public class Splash extends AppCompatActivity {

    private SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = new SessionManagement(getApplicationContext());

        firstTimeCheck();

    }

    private void firstTimeCheck() {

        if (session.FirstTime()) {

            Intent go = new Intent(Splash.this, Intro.class);

            finish();

            startActivity(go);

            session.unsetFirstTime(false);

        }

        else {

            Intent go = new Intent(Splash.this, LoginRegisterTabbed.class);

            finish();

            startActivity(go);


        }

    }

}
