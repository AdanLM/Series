package com.adanlm.series.data.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "seasons")
public class Season {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private int idSeason;

    @SerializedName("number")
    @Expose
    private int numberSeason;

    private int idShow;

    @Ignore
    public Season(int idSeason, int numberSeason) {
        this.idSeason = idSeason;
        this.numberSeason = numberSeason;
    }

    public Season(int idShow, int idSeason, int numberSeason) {
        this.idSeason = idSeason;
        this.numberSeason = numberSeason;
        this.idShow = idShow;
    }

    public int getIdSeason() {
        return idSeason;
    }

    public void setIdSeason(int idSeason) {
        this.idSeason = idSeason;
    }

    public int getNumberSeason() {
        return numberSeason;
    }

    public void setNumberSeason(int numberSeason) {
        this.numberSeason = numberSeason;
    }

    public int getIdShow() {
        return idShow;
    }

    public void setIdShow(int idShow) {
        this.idShow = idShow;
    }
}
