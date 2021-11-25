package com.adanlm.series.ui.main;

import com.adanlm.series.data.ShowsRepository;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class MainModule {


    @Provides
    static MainContract.Presenter providesPresenter(ShowsRepository repository) {
        return new MainPresenter(repository);
    }
}
