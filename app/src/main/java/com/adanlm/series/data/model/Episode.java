package com.adanlm.series.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "episodes")
public class Episode {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("season")
    @Expose
    @ColumnInfo(name = "idSeason")
    private int season;

    @PrimaryKey
    @SerializedName("number")
    @Expose
    private int numEpisode;

    @SerializedName("summary")
    @Expose
    private String summary;

    @SerializedName("image")
    @Expose
    private Image image;

    public Episode(String name, int season, int numEpisode, String summary, Image image) {
        this.name = name;
        this.season = season;
        this.numEpisode = numEpisode;
        this.summary = summary;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getNumEpisode() {
        return numEpisode;
    }

    public void setNumEpisode(int numEpisode) {
        this.numEpisode = numEpisode;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }


}
