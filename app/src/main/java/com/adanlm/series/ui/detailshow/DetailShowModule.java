package com.adanlm.series.ui.detailshow;

import com.adanlm.series.data.ShowsRepository;
import com.adanlm.series.ui.adapters.SeasonAdapter;

import dagger.Module;
import dagger.Provides;

import static com.adanlm.series.utils.Const.EXTRA_SHOW_STRING;

@Module
public class DetailShowModule {

    @Provides
    static DetailShowContract.Presenter providesPresenter(ShowsRepository repository, String showString) {
        return new DetailShowPresenter(repository, showString);
    }

    @Provides
    static String provideJsonString(DetailShowActivity activity) {
        return activity.getIntent().getStringExtra(EXTRA_SHOW_STRING);
    }

    @Provides
    static SeasonAdapter provideAdapter() {
        return new SeasonAdapter();
    }
}
