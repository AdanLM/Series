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

    @SerializedName("premiered")
    @Expose
    private String premiered;

    @SerializedName("officialSite")
    @Expose
    private String officialSite;

    @SerializedName("rating")
    @Expose
    private Rating rating;

    public Show(int idShow, String title, String summary, List<String> genres, Image image, String premiered, String officialSite, Rating rating) {
        this.idShow = idShow;
        this.title = title;
        this.summary = summary;
        this.genres = genres;
        this.image = image;
        this.premiered = premiered;
        this.officialSite = officialSite;
        this.rating = rating;
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

    public String getPremiered() {
        return premiered;
    }

    public String getOfficialSite() {
        return officialSite;
    }

    public float getRating() {
        return rating.getAverage();
    }
}
