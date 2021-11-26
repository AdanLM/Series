package com.adanlm.series.data;

import android.content.Context;

import com.adanlm.series.data.local.ControlDataBase;
import com.adanlm.series.data.model.Episode;
import com.adanlm.series.data.model.Season;
import com.adanlm.series.data.model.Show;
import com.adanlm.series.data.remote.EndPoints;
import com.adanlm.series.utils.CommonUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class ShowsRepository {

    private final EndPoints endPoints;
    private final ControlDataBase db;
    private Context context;


    @Inject
    public ShowsRepository(EndPoints endPoints, ControlDataBase db, Context context) {
        this.endPoints = endPoints;
        this.db = db;
        this.context = context;
    }

    public Single<List<Show>> getAllShows() {
        if (CommonUtils.isNetworkAvailable(context)) {
            return endPoints.getAllShows();
        }
        return db.getShowDao().getAllShow();
    }

    public Single<List<Season>> getAllSeasonByShow(int idShow) {
        if (CommonUtils.isNetworkAvailable(context)) {
            return endPoints.getSeasonByShows(idShow);
        }
        return db.getSeasonDao().getAllSeasonByShow(idShow);
    }

    public Single<List<Episode>> getAllEpisodesBySeason(int idSeason) {
        if (CommonUtils.isNetworkAvailable(context)) {
            return endPoints.getEpisodesBySeason(idSeason);
        }
        return db.getEpisodeDao().getAllEpisodesBySeason(idSeason);
    }

    public void insertAllShow(List<Show> showList) {
        ControlDataBase.dbExecutor.execute(() -> {
            db.getShowDao().insertAllShow(showList);
        });
    }

    public void insertAllSeason(List<Season> seasonList) {
        ControlDataBase.dbExecutor.execute(() -> {
            db.getSeasonDao().insertAllSeason(seasonList);
        });
    }

    public void insertAllEpisodes(List<Episode> episodesList) {
        ControlDataBase.dbExecutor.execute(() -> {
            db.getEpisodeDao().insertAllEpisode(episodesList);
        });
    }
}
