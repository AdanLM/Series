package com.adanlm.series.ui.detailshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.adanlm.series.R;
import com.adanlm.series.data.model.Season;
import com.adanlm.series.data.model.Show;
import com.adanlm.series.ui.adapters.SeasonAdapter;
import com.adanlm.series.ui.detailseason.DetailSeasonActivity;
import com.adanlm.series.utils.CommonUtils;
import com.bumptech.glide.RequestManager;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

import static com.adanlm.series.utils.Const.EXTRA_ID_SEASON;

public class DetailShowActivity extends DaggerAppCompatActivity implements DetailShowContract.View, DetailShowContract.OnItemClickListener {

    private static final String TAG = "DetailShowActivity";

    @Inject
    DetailShowContract.Presenter presenter;

    @Inject
    RequestManager glide;

    @Inject
    SeasonAdapter adapter;

    private TextView txtSummary, txtGenres, txtPremieredDate, txtOfficialSite, txtRating;
    private RatingBar rbarRating;
    private ImageView imgPreview;
    private RecyclerView recyclerView;
    private RelativeLayout linearLayout;
    private TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_show);

        txtSummary = findViewById(R.id.txt_detail_summary);
        txtGenres = findViewById(R.id.txt_detail_genres);
        txtPremieredDate = findViewById(R.id.txt_detail_premiered_date);
        txtOfficialSite = findViewById(R.id.txt_detail_official_site);
        txtRating = findViewById(R.id.txt_detail_rating);
        rbarRating = findViewById(R.id.rbar_detail_rating);
        imgPreview = findViewById(R.id.img_detail);
        recyclerView = findViewById(R.id.show_list_season);
        linearLayout = findViewById(R.id.linear_detail_show);

        emptyView = findViewById(R.id.empty_view_detail_show);

        recyclerView.setAdapter(adapter);
        adapter.setListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        presenter.takeView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.dropView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!CommonUtils.isNetworkAvailable(this)) {
            showSnackDontInternet();
        }
    }

    @Override
    public void showDetailShow(Show show) {

        txtSummary.setText(CommonUtils.cleanHTMLText(show.getSummary()));
        txtGenres.setText(CommonUtils.cleanBracketsText(show.getGenres().toString()));
        txtPremieredDate.setText(show.getPremiered());
        txtOfficialSite.setText(show.getOfficialSite());
        txtRating.setText(String.valueOf(show.getRating().getAverage()));
        rbarRating.setRating(show.getRating().getAverage());
        glide.load(show.getImage().getOriginal()).into(imgPreview);
    }

    @Override
    public void setTitleActivity(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void showSeasons(List<Season> seasons) {
        emptyView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        adapter.updateData(seasons);
    }

    private void showSnackDontInternet() {
        Snackbar snackbar = Snackbar.make(linearLayout, R.string.error_connection, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("X", view -> {
            snackbar.dismiss();
        });
        snackbar.show();
    }

    @Override
    public void onClick(Season season) {
        Intent intent = new Intent(DetailShowActivity.this, DetailSeasonActivity.class);
        intent.putExtra(EXTRA_ID_SEASON, season.getIdSeason());
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void showEmptyView() {
        recyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }
}