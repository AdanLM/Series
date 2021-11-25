package com.adanlm.series.ui.detailseason;

import com.adanlm.series.data.ShowsRepository;
import com.adanlm.series.ui.adapters.EpisodeAdapter;
import com.bumptech.glide.RequestManager;

import dagger.Module;
import dagger.Provides;

import static com.adanlm.series.utils.Const.EXTRA_ID_SEASON;

@Module
public class DetailSeasonModule {

    @Provides
    static DetailSeasonContract.Presenter providesPresenter(ShowsRepository repository, int idSeason) {
        return new DetailSeasonPresenter(repository, idSeason);
    }

    @Provides
    static int provideidSeason(DetailSeasonActivity activity) {
        return activity.getIntent().getIntExtra(EXTRA_ID_SEASON, 0);
    }

    @Provides
    static EpisodeAdapter provideAdapter(RequestManager requestManager) {
        return new EpisodeAdapter(requestManager);
    }
}
