package com.adanlm.series.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.adanlm.series.data.model.Show;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface ShowDao {

    @Query("SELECT * FROM shows ORDER BY idShow ASC")
    Single<List<Show>> getAllShow();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertShow(Show show);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAllShow(List<Show> show);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updateShow(Show show);

    @Delete
    void deleteShow(Show show);
}
