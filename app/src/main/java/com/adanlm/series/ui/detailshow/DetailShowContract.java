package com.adanlm.series.ui.detailshow;

import com.adanlm.series.data.model.Show;

public interface DetailShowContract {

    interface View {
        void showDetailShow(Show show);
        void setTitleActivity(String title);
    }

    interface Presenter {
        void takeView(View view);

        void dropView();

    }
}
