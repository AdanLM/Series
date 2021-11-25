package com.adanlm.series.ui.detailshow;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.adanlm.series.R;

import dagger.android.support.DaggerAppCompatActivity;

public class DetailShowActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_show);
    }
}