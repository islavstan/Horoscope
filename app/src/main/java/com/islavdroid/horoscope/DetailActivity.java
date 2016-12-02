package com.islavdroid.horoscope;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.islavdroid.horoscope.adapter.ViewPagerAdapter;

public class DetailActivity extends AppCompatActivity {
    public static String sunsign;
    TabLayout tab_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        tab_layout.addTab(tab_layout.newTab().setText("today"));
        tab_layout.addTab(tab_layout.newTab().setText("week"));
        tab_layout.addTab(tab_layout.newTab().setText("month"));
        tab_layout.addTab(tab_layout.newTab().setText("year"));
        tab_layout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager view_pager = (ViewPager) findViewById(R.id.pager);
        // view_pager.setOffscreenPageLimit(3);
        final ViewPagerAdapter adapter = new ViewPagerAdapter
                (getSupportFragmentManager(), tab_layout.getTabCount());

        view_pager.setAdapter(adapter);

        view_pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_layout));
        Intent intent = getIntent();
        sunsign = intent.getStringExtra("sunsign");
        setTitle(sunsign);
        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                view_pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
           onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
