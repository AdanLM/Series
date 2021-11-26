package com.adanlm.series.ui.detailshow;

import android.util.Log;

import com.adanlm.series.data.ShowsRepository;
import com.adanlm.series.data.model.Season;
import com.adanlm.series.data.model.Show;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailShowPresenter implements DetailShowContract.Presenter {

    private static final String TAG = "DetailShowPresenter";

    private DetailShowContract.View view;
    private ShowsRepository repository;
    private Show showDetail;
    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    public DetailShowPresenter(ShowsRepository repository, String showDetail) {
        this.repository = repository;
        if (!showDetail.isEmpty()) {
            Gson gson = new Gson();
            this.showDetail = gson.fromJson(showDetail, Show.class);
        }
    }

    @Override
    public void takeView(DetailShowContract.View view) {
        this.view = view;
        if (showDetail != null) {
            view.showDetailShow(showDetail);
            view.setTitleActivity(showDetail.getTitle());
        }
    }

    @Override
    public void getAllSeasonsByShow() {
        repository.getAllSeasonByShow(showDetail.getIdShow())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Season>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<Season> seasons) {
                        Log.d(TAG, "onSuccess: " + seasons.size());
                        view.showSeasons(seasons);
                        for (Season season : seasons) {
                            season.setIdShow(showDetail.getIdShow());
                        }
                        repository.insertAllSeason(seasons);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: ");
                    }
                });
    }

    @Override
    public void dropView() {
        this.view = null;
        if (!disposable.isDisposed()) {
            disposable.clear();
        }
    }
}
