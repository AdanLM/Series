package com.adanlm.series.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Season {

    @SerializedName("id")
    @Expose
    private int idSeason;

    @SerializedName("number")
    @Expose
    private int numberSeason;

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
}
