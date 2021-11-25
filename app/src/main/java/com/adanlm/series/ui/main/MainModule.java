package com.adanlm.series.ui.main;

import com.adanlm.series.data.ShowsRepository;
import com.adanlm.series.ui.adapters.ShowAdapter;
import com.bumptech.glide.RequestManager;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class MainModule {


    @Provides
    static MainContract.Presenter providesPresenter(ShowsRepository repository) {
        return new MainPresenter(repository);
    }

    @Provides
    static ShowAdapter provideAdapter(RequestManager requestManager) {
        return new ShowAdapter(requestManager);
    }
}
