package com.adanlm.series.ui.main;

import android.util.Log;

import com.adanlm.series.data.ShowsRepository;
import com.adanlm.series.data.model.Show;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = "MainPresenter";

    private MainContract.View view;
    private final ShowsRepository repository;
    private CompositeDisposable disposable;

    public MainPresenter(ShowsRepository repository) {
        this.repository = repository;
        this.disposable = new CompositeDisposable();
    }

    @Override
    public void takeView(MainContract.View view) {
        this.view = view;
        getAllShows();
    }

    @Override
    public void dropView() {
        this.view = null;
        if (!disposable.isDisposed()) {
            disposable.clear();
        }
    }

    @Override
    public void getAllShows() {
        repository.getAllShows()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Show>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<Show> shows) {
                        view.showAllShows(shows);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: ");
                    }
                });
    }
}
