package com.adanlm.series.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.adanlm.series.data.model.Season;
import com.adanlm.series.data.model.Show;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface SeasonDao {

    @Query("SELECT * FROM seasons ORDER BY idSeason ASC")
    Single<List<Season>> getAllSeason();

    @Query("SELECT * FROM seasons WHERE idShow = :idShow ORDER BY idSeason ASC")
    Single<List<Season>> getAllSeasonByShow(int idShow);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSeason(Season season);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllSeason(List<Season> season);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updateSeason(Season season);

    @Delete
    void deleteSeason(Season season);
}
