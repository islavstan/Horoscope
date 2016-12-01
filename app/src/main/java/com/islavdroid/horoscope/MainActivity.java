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
    @BindView(R.id.aquarius_block) RelativeLayout aquarius_block;
    @BindView(R.id.pisces_block) RelativeLayout pisces_block;
    @BindView(R.id.aries_block) RelativeLayout aries_block;
    @BindView(R.id.taurus_block) RelativeLayout taurus_block;
    @BindView(R.id.gemini_block) RelativeLayout gemini_block;
    @BindView(R.id.cancer_block) RelativeLayout cancer_block;
    @BindView(R.id.leo_block) RelativeLayout leo_block;
    @BindView(R.id.virgo_block) RelativeLayout virgo_block;
    @BindView(R.id.libra_block) RelativeLayout libra_block;
    @BindView(R.id.scorpio_block) RelativeLayout scorpio_block;
    @BindView(R.id.sagittarius_block) RelativeLayout sagittarius_block;
    private final String sunsign ="sunsign";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }



    @OnClick({R.id.capricorn_block,R.id.aquarius_block,R.id.pisces_block,R.id.aries_block,R.id.taurus_block,
            R.id.gemini_block,R.id.cancer_block,R.id.leo_block,R.id.virgo_block,
            R.id.libra_block,R.id.scorpio_block,R.id.sagittarius_block})
    public void toDetAct(RelativeLayout layout){
        switch (layout.getId()){
            case R.id.capricorn_block:
                intent("capricorn");
                break;
            case R.id.aquarius_block:
                intent("aquarius");
                break;
            case R.id.pisces_block:
                intent("pisces");
                break;
            case R.id.aries_block:
                intent("aries");
                break;
            case R.id.taurus_block:
                intent("taurus");
                break;
            case R.id.gemini_block:
                intent("gemini");
                break;
            case R.id.cancer_block:
                intent("cancer");
                break;
            case R.id.leo_block:
                intent("leo");
                break;
            case R.id.virgo_block:
                intent("virgo");
                break;
            case R.id.libra_block:
                intent("libra");
                break;
            case R.id.scorpio_block:
                intent("scorpio");
                break;
            case R.id.sagittarius_block:
                intent("sagittarius");
                break;
        }

    }





    private void intent(String sunSign){
        Intent intent =new Intent(MainActivity.this,DetailActivity.class);
        intent.putExtra(sunsign,sunSign);
        startActivity(intent);
    }


}
