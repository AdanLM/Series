package com.adanlm.series.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Episode {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("season")
    @Expose
    private String season;

    @SerializedName("number")
    @Expose
    private String numEpisode;

    @SerializedName("summary")
    @Expose
    private String summary;

    @SerializedName("image")
    @Expose
    private Image image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getNumEpisode() {
        return numEpisode;
    }

    public void setNumEpisode(String numEpisode) {
        this.numEpisode = numEpisode;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImage() {
        return image.getMedium();
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
