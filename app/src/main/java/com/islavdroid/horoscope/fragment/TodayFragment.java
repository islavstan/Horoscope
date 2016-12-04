package com.islavdroid.horoscope.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.islavdroid.horoscope.Ads;
import com.islavdroid.horoscope.DetailActivity;
import com.islavdroid.horoscope.R;
import com.islavdroid.horoscope.api.ApiClient;
import com.islavdroid.horoscope.model.Horoscope;
import com.islavdroid.horoscope.point.TodayPoint;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TodayFragment  extends Fragment {
    @BindView(R.id.horoscope) TextView horoscope;
    @BindView(R.id.date) TextView date;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_today, container, false);
        ButterKnife.bind(this, v);
        Ads.showBanner(v);
      /*  Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/ArefRuqaa-Regular.ttf");
        horoscope.setTypeface(custom_font);
        date.setTypeface(custom_font);*/
        final TodayPoint apiService = ApiClient.getHoroscope().create(TodayPoint.class);
        Call<Horoscope> call =apiService.getTodayHoroscope(DetailActivity.sunsign);
        call.enqueue(new Callback<Horoscope>() {
            @Override
            public void onResponse(Call<Horoscope> call, Response<Horoscope> response) {
                Horoscope today =response.body();
                String horoscopeText = today.getHoroscope();
                String redactHoroscopeText =horoscopeText.replace("['","").replace("[u'","").
                        replace("Ganesha","Astrologer").replace("\\u"," ").replace("..",".");



                horoscope.setText(redactHoroscopeText);
                date.setText(today.getDate());

            }

            @Override
            public void onFailure(Call<Horoscope> call, Throwable t) {

            }
        });
        return v;
    }}
