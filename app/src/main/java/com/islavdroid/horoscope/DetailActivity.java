package com.islavdroid.horoscope;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.islavdroid.horoscope.adapter.ViewPagerAdapter;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TabLayout tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        tab_layout.addTab(tab_layout.newTab().setText("today"));
        tab_layout.addTab(tab_layout.newTab().setText("week"));
        tab_layout.addTab(tab_layout.newTab().setText("month"));
        tab_layout.addTab(tab_layout.newTab().setText("year"));
        final ViewPager view_pager = (ViewPager) findViewById(R.id.pager);
        view_pager.setOffscreenPageLimit(3);
        final ViewPagerAdapter adapter = new ViewPagerAdapter
                (getSupportFragmentManager(), tab_layout.getTabCount());

        view_pager.setAdapter(adapter);

        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //switch (state){
                   // case ViewPager.SCROLL_STATE_IDLE:
                       // fab.showMenuButton(true);
                      //  break;
                }//}
        });
    }
}
