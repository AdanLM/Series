package com.adanlm.series.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.adanlm.series.R;
import com.adanlm.series.data.model.Show;
import com.adanlm.series.ui.adapters.ShowAdapter;
import com.adanlm.series.ui.detailshow.DetailShowActivity;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

import static com.adanlm.series.utils.Const.EXTRA_SHOW_STRING;

public class MainActivity extends DaggerAppCompatActivity implements MainContract.View, MainContract.OnItemClickListener {

    private static final String TAG = "MainActivity";

    @Inject
    MainContract.Presenter presenter;

    @Inject
    ShowAdapter adapter;

    private RecyclerView showRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showRecycler = findViewById(R.id.show_list);
        showRecycler.setAdapter(adapter);
        adapter.setListener(this);
        presenter.takeView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.dropView();
    }

    @Override
    public void showAllShows(List<Show> showsList) {
        adapter.updateData(showsList);
    }

    @Override
    public void onClick(Show show) {
        String jsonShow = new Gson().toJson(show);
        Intent intent = new Intent(MainActivity.this, DetailShowActivity.class);
        intent.putExtra(EXTRA_SHOW_STRING, jsonShow);
        startActivity(intent);
    }
}