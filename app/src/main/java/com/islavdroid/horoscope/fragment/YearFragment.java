package com.islavdroid.horoscope.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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


public class YearFragment extends Fragment {
    @BindView(R.id.horoscope)
    TextView horoscope;
    @BindView(R.id.date) TextView date;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_year, container, false);
        ButterKnife.bind(this, v);

        final TodayPoint apiService = ApiClient.getHoroscope().create(TodayPoint.class);
        Call<Horoscope> call =apiService.getYearHoroscope(DetailActivity.sunsign);
        call.enqueue(new Callback<Horoscope>() {
            @Override
            public void onResponse(Call<Horoscope> call, Response<Horoscope> response) {
                Horoscope year =response.body();
                String horoscopeText = year.getHoroscope();
                String redactHoroscopeText =horoscopeText.replace("['","");

                horoscope.setText(redactHoroscopeText);
                date.setText(year.getYear());

            }

            @Override
            public void onFailure(Call<Horoscope> call, Throwable t) {

            }
        });
        return v;
    }}