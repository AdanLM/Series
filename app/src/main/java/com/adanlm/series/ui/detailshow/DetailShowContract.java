package com.adanlm.series.ui.detailshow;

import com.adanlm.series.data.model.Season;
import com.adanlm.series.data.model.Show;

import java.util.List;

public interface DetailShowContract {

    interface View {
        void showDetailShow(Show show);

        void setTitleActivity(String title);

        void showSeasons(List<Season> seasons);

        void showEmptyView();
    }

    interface Presenter {
        void takeView(View view);

        void dropView();

        void getAllSeasonsByShow();

    }

    interface OnItemClickListener {
        void onClick(Season season);
    }
}
