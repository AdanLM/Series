package com.adanlm.series.ui.detailseason;

import android.util.Log;

import com.adanlm.series.data.ShowsRepository;
import com.adanlm.series.data.model.Episode;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailSeasonPresenter implements DetailSeasonContract.Presenter {

    private static final String TAG = "DetailSeasonPresenter";
    private ShowsRepository repository;
    private int idSeason;
    private CompositeDisposable disposable = new CompositeDisposable();
    private DetailSeasonContract.View view;

    @Inject
    public DetailSeasonPresenter(ShowsRepository repository, int idSeason) {
        this.repository = repository;
        this.idSeason = idSeason;
    }

    @Override
    public void takeView(DetailSeasonContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
        if (!disposable.isDisposed()) {
            disposable.clear();
        }
    }

    @Override
    public void getAllEpisodesBySeason() {
        view.showProgress();
        repository.getAllEpisodesBySeason(idSeason)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Episode>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<Episode> episodeList) {
                        view.hideProgress();
                        if (episodeList.size() > 0) {
                            view.showEpisodes(episodeList);
                            view.setTitleActivity("Temporada " + idSeason);
                            repository.insertAllEpisodes(episodeList);
                        } else {
                            view.showEmptyView();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: ");
                        view.showProgress();
                        view.showEmptyView();
                    }
                });
    }
}
