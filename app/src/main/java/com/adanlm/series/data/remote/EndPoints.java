package com.adanlm.series.data.remote;


import com.adanlm.series.data.model.Season;
import com.adanlm.series.data.model.Show;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EndPoints {

    @GET("shows")
    Single<List<Show>> getAllShows();

    @GET("shows/{idShow}/seasons")
    Single<List<Season>> getSeasonByShows(@Path("idShow") int idShow);
}
