package com.codevars.a2o;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import com.codevars.a2o.Adapters.ViewPagerAdapter;


public class LoginRegisterTabbed extends AppCompatActivity {


    private Toolbar toolbar;

    private TabLayout tablayout;

    private ViewPager viewpager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register_tabbed);


        viewpager = (ViewPager) findViewById(R.id.viewpager);

        setupViewPager(viewpager);

        tablayout = (TabLayout) findViewById(R.id.tablayout);

        tablayout.setupWithViewPager(viewpager);

        hideStatusBar();


    }



    private void hideStatusBar() {

        Window window = getWindow();

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.setStatusBarColor(Color.BLACK);

    }



    public void setupViewPager(ViewPager viewpager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new Login(), "Login");

        adapter.addFragment(new Register(), "Register");

        viewpager.setAdapter(adapter);

    }



}

