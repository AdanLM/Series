package com.adanlm.series.data;

import com.adanlm.series.data.local.ControlDataBase;
import com.adanlm.series.data.model.Episode;
import com.adanlm.series.data.model.Season;
import com.adanlm.series.data.model.Show;
import com.adanlm.series.data.remote.EndPoints;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class ShowsRepository {

    private final EndPoints endPoints;
    private final ControlDataBase db;


    @Inject
    public ShowsRepository(EndPoints endPoints, ControlDataBase db) {
        this.endPoints = endPoints;
        this.db = db;
    }

    public Single<List<Show>> getAllShows() {
        return endPoints.getAllShows();
    }

    public Single<List<Season>> getAllSeasonByShow(int idShow) {
        return endPoints.getSeasonByShows(idShow);
    }

    public Single<List<Episode>> getAllEpisodesBySeason(int idSeason) {
        return endPoints.getEpisodesBySeason(idSeason);
    }

    public void insertShow(Show show) {
        ControlDataBase.dbExecutor.execute(() -> {
            db.getShowDao().insertShow(show);
        });
    }

    public void insertAllShow(List<Show> showList) {
        ControlDataBase.dbExecutor.execute(() -> {
            db.getShowDao().insertAllShow(showList);
        });
    }
}
