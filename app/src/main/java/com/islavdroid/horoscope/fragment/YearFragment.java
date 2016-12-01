package com.islavdroid.horoscope.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.islavdroid.horoscope.R;

/**
 * Created by islav on 01.12.2016.
 */

public class YearFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_year, container, false);
        return v;
    }}
