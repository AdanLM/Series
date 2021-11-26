package com.adanlm.series.ui.main;

import com.adanlm.series.data.model.Show;

import java.util.List;

public interface MainContract {

    interface View {
        void showAllShows(List<Show> showsList);
        void showProgress();
        void hideProgress();
        void showEmptyView();
    }

    interface Presenter {

        void takeView(View view);

        void dropView();

        void getAllShows();
    }

    interface OnItemClickListener {
        void onClick(Show show);
    }
}
