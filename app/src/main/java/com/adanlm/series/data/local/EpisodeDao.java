package com.adanlm.series.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.adanlm.series.data.model.Episode;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface EpisodeDao {

    @Query("SELECT * FROM episodes ORDER BY numEpisode ASC")
    Single<List<Episode>> getAllEpisodes();

    @Query("SELECT * FROM episodes WHERE idSeason = :idSeason ORDER BY numEpisode ASC")
    Single<List<Episode>> getAllEpisodesBySeason(int idSeason);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertEpisode(Episode episode);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAllEpisode(List<Episode> episode);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updateEpisode(Episode season);

    @Delete
    void deleteEpisode(Episode season);
}
