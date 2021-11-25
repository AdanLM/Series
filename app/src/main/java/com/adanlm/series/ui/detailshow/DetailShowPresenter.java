package com.adanlm.series.ui.detailshow;

import com.adanlm.series.data.ShowsRepository;
import com.adanlm.series.data.model.Show;
import com.google.gson.Gson;

import javax.inject.Inject;

public class DetailShowPresenter implements DetailShowContract.Presenter {

    private static final String TAG = "DetailShowPresenter";

    private DetailShowContract.View view;
    private ShowsRepository repository;
    private String showDetail;

    @Inject
    public DetailShowPresenter(ShowsRepository repository, String showDetail) {
        this.repository = repository;
        this.showDetail = showDetail;
    }

    @Override
    public void takeView(DetailShowContract.View view) {
        this.view = view;
        if (!showDetail.isEmpty()) {
            Gson gson = new Gson();
            Show show = gson.fromJson(showDetail, Show.class);
            view.showDetailShow(show);
            view.setTitleActivity(show.getTitle());
        }
    }

    @Override
    public void dropView() {
        this.view = null;
    }
}
