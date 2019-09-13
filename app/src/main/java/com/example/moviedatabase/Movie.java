package com.example.moviedatabase;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("original_title")
    private String title;

    @SerializedName("vote_average")
    private double voteAvg;

    @SerializedName("release_date")
    private String dateRel;

    @SerializedName("original_language")
    private String lang;

    private String overview;

    @SerializedName("popularity")
    private double views;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getVoteAvg() {
        return voteAvg;
    }

    public void setVoteAvg(double voteAvg) {
        this.voteAvg = voteAvg;
    }

    public String getDateRel() {
        return dateRel;
    }

    public void setDateRel(String dateRel) {
        this.dateRel = dateRel;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getViews() {
        return views;
    }

    public void setViews(double views) {
        this.views = views;
    }
}
