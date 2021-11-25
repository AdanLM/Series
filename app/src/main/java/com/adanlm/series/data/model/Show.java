package com.adanlm.series.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Show {

    @SerializedName("id")
    @Expose
    private int idShow;
    @SerializedName("name")
    @Expose
    private String title;
    @SerializedName("summary")
    @Expose
    private String summary;

    @SerializedName("genres")
    @Expose
    private List<String> genres;

    @SerializedName("image")
    @Expose
    private Image image;

    public Show(int idShow, String title, String summary, List<String> genres, Image image) {
        this.idShow = idShow;
        this.title = title;
        this.summary = summary;
        this.genres = genres;
        this.image = image;
    }

    public Show() {
    }

    public int getIdShow() {
        return idShow;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public List<String> getGenres() {
        return genres;
    }

    public Image getImage() {
        return image;
    }
}
