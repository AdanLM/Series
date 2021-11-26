package com.adanlm.series.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.adanlm.series.BuildConfig;
import com.adanlm.series.data.model.Episode;
import com.adanlm.series.data.model.Season;
import com.adanlm.series.data.model.Show;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Show.class, Season.class, Episode.class}, version = BuildConfig.DATABASE_VERSION, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class ControlDataBase extends RoomDatabase {

    public static ExecutorService dbExecutor = Executors.newFixedThreadPool(4);

    public abstract ShowDao getShowDao();

    public abstract SeasonDao getSeasonDao();

    public abstract EpisodeDao getEpisodeDao();
}
