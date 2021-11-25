package com.adanlm.series.ui.main;

import android.os.Bundle;

import com.adanlm.series.R;
import com.adanlm.series.data.model.Show;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements MainContract.View {

    private static final String TAG = "MainActivity";

    @Inject
    MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        presenter.takeView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getAllShows();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.dropView();
    }

    @Override
    public void showAllShows(List<Show> showsList) {

    }
}