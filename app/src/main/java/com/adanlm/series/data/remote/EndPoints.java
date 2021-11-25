package com.adanlm.series.data.remote;


import com.adanlm.series.data.model.Show;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface EndPoints {

    @GET("shows")
    Single<List<Show>> getAllShows();
}
