package com.codevars.a2o;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class LoginRegisterTabbed extends AppCompatActivity {

    private Toolbar toolbar;

    private TabLayout tablayout;

    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register_tabbed);


        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        viewpager = (ViewPager) findViewById(R.id.viewpager);

        setupViewPager(viewpager);

        tablayout = (TabLayout) findViewById(R.id.tablayout);

        tablayout.setupWithViewPager(viewpager);


    }



    public void setupViewPager(ViewPager viewpager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new Login(), "Login");

        adapter.addFragment(new Register(), "Register");

        viewpager.setAdapter(adapter);

    }



    public class ViewPagerAdapter extends FragmentPagerAdapter  {

        private final List<Fragment> fragments = new ArrayList<>();

        private final List<String> titles = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {

            super(manager);

        }

        @Override
        public Fragment getItem(int position) {

            return fragments.get(position);

        }

        @Override
        public int getCount() {

            return titles.size();

        }

        @Override
        public CharSequence getPageTitle(int position) {

            return titles.get(position);

        }

        public void addFragment(Fragment fragment, String string) {

            fragments.add(fragment);

            titles.add(string);

        }


    }



}
