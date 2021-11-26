package com.adanlm.series.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.adanlm.series.R;
import com.adanlm.series.data.model.Show;
import com.adanlm.series.ui.adapters.ShowAdapter;
import com.adanlm.series.ui.detailshow.DetailShowActivity;
import com.adanlm.series.utils.CommonUtils;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

import static com.adanlm.series.utils.Const.EXTRA_SHOW_STRING;

public class MainActivity extends DaggerAppCompatActivity implements MainContract.View, MainContract.OnItemClickListener, SearchView.OnQueryTextListener {

    private static final String TAG = "MainActivity";

    @Inject
    MainContract.Presenter presenter;

    @Inject
    ShowAdapter adapter;

    private RecyclerView showRecycler;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showRecycler = findViewById(R.id.show_list);
        showRecycler.setAdapter(adapter);
        linearLayout = findViewById(R.id.linear_main);

        adapter.setListener(this);
        presenter.takeView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!CommonUtils.isNetworkAvailable(this)) {
            showSnackDontInternet();
        }
        presenter.getAllShows();
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

    private void showSnackDontInternet() {
        Snackbar snackbar = Snackbar.make(linearLayout, R.string.error_connection, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("X", view -> {
            snackbar.dismiss();
        });
        snackbar.show();
    }

    @Override
    public void onClick(Show show) {
        String jsonShow = new Gson().toJson(show);
        Intent intent = new Intent(MainActivity.this, DetailShowActivity.class);
        intent.putExtra(EXTRA_SHOW_STRING, jsonShow);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String searchString) {
        adapter.getFilter().filter(searchString);
        return false;
    }


}