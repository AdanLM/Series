package com.adanlm.series.ui.main;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.adanlm.series.R;
import com.adanlm.series.data.model.Show;
import com.adanlm.series.ui.adapters.ShowAdapter;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements MainContract.View {

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
}