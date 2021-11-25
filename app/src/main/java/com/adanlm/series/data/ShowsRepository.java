package com.adanlm.series.data;

import com.adanlm.series.data.model.Show;
import com.adanlm.series.data.remote.EndPoints;

import java.util.List;

import io.reactivex.Single;

public class ShowsRepository {

    private final EndPoints endPoints;

    public ShowsRepository(EndPoints endPoints) {
        this.endPoints = endPoints;
    }

    public Single<List<Show>> getAllShows() {
        return endPoints.getAllShows();
    }
}
