package com.adanlm.series.ui.detailseason;

import com.adanlm.series.data.model.Episode;

import java.util.List;

public interface DetailSeasonContract {

    interface View {
        void showEpisodes(List<Episode> episodeList);

        void setTitleActivity(String title);

        void showEmptyView();
    }

    interface Presenter {
        void takeView(View view);

        void dropView();

        void getAllEpisodesBySeason();
    }
}
