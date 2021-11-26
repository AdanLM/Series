package com.adanlm.series.ui.detailseason;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.adanlm.series.R;
import com.adanlm.series.data.model.Episode;
import com.adanlm.series.ui.adapters.EpisodeAdapter;
import com.adanlm.series.utils.CommonUtils;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class DetailSeasonActivity extends DaggerAppCompatActivity implements DetailSeasonContract.View {

    @Inject
    DetailSeasonContract.Presenter presenter;

    @Inject
    EpisodeAdapter adapter;

    private RecyclerView recyclerView;

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_season);

        recyclerView = findViewById(R.id.show_list_episodes);
        recyclerView.setAdapter(adapter);

        linearLayout = findViewById(R.id.linear_detail_season);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        presenter.takeView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!CommonUtils.isNetworkAvailable(this)) {
            showSnackDontInternet();
        }
        presenter.getAllEpisodesBySeason();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.dropView();
    }

    @Override
    public void showEpisodes(List<Episode> episodeList) {
        adapter.updateData(episodeList);
    }

    @Override
    public void setTitleActivity(String title) {
        getSupportActionBar().setTitle(title);
    }

    private void showSnackDontInternet() {
        Snackbar snackbar = Snackbar.make(linearLayout, R.string.error_connection, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("X", view -> {
            snackbar.dismiss();
        });
        snackbar.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}