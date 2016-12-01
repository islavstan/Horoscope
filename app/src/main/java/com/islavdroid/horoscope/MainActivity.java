package com.islavdroid.horoscope;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.capricorn_block) RelativeLayout capricorn_block;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }



    @OnClick(R.id.capricorn_block)
    public void toDetAct(){
        Intent intent =new Intent(MainActivity.this,DetailActivity.class);
        startActivity(intent);
    }
}
