package com.adanlm.series.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.adanlm.series.BuildConfig;
import com.adanlm.series.data.model.Show;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Show.class}, version = BuildConfig.DATABASE_VERSION, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class ControlDataBase extends RoomDatabase {

    public static ExecutorService dbExecutor = Executors.newFixedThreadPool(4);

    public abstract ShowDao getShowDao();
}
