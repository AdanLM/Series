package com.adanlm.series.ui.detailshow;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.adanlm.series.R;
import com.adanlm.series.data.model.Show;
import com.adanlm.series.utils.CommonUtils;
import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class DetailShowActivity extends DaggerAppCompatActivity implements DetailShowContract.View {

    @Inject
    DetailShowContract.Presenter presenter;

    @Inject
    RequestManager glide;

    private TextView txtSummary, txtGenres, txtPremieredDate, txtOfficialSite, txtRating;
    private RatingBar rbarRating;
    private ImageView imgPreview;

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

        presenter.takeView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.dropView();
    }

    @Override
    public void showDetailShow(Show show) {

        txtSummary.setText(CommonUtils.cleanHTMLText(show.getSummary()));
        txtGenres.setText(CommonUtils.cleanBracketsText(show.getGenres().toString()));
        txtPremieredDate.setText(show.getPremiered());
        txtOfficialSite.setText(show.getOfficialSite());
        txtRating.setText(String.valueOf(show.getRating()));
        rbarRating.setRating(show.getRating());
        glide.load(show.getImage().getOriginal()).into(imgPreview);
    }

    @Override
    public void setTitleActivity(String title) {
        getSupportActionBar().setTitle(title);
    }
}