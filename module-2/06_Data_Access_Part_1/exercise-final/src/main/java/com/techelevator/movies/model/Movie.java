package com.techelevator.movies.model;

import java.time.LocalDate;

public class Movie {

    private int id;
    private String title;
    private String overview;
    private String tagline;
    private String posterPath;
    private String homePage;
    private LocalDate releaseDate;
    private int lengthMinutes;
    private int directorId;
    private int collectionId;

    public Movie() {
    }

    public Movie(int id, String title, String overview, String tagline, String posterPath, String homePage,
                 LocalDate releaseDate, int lengthMinutes, int directorId, int collectionId) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.tagline = tagline;
        this.posterPath = posterPath;
        this.homePage = homePage;
        this.releaseDate = releaseDate;
        this.lengthMinutes = lengthMinutes;
        this.directorId = directorId;
        this.collectionId = collectionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getLengthMinutes() {
        return lengthMinutes;
    }

    public void setLengthMinutes(int lengthMinutes) {
        this.lengthMinutes = lengthMinutes;
    }

    public int getDirectorId() {
        return directorId;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", tagline='" + tagline + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", homePage='" + homePage + '\'' +
                ", releaseDate=" + releaseDate +
                ", length=" + lengthMinutes +
                ", directorId=" + directorId +
                ", collectionId=" + collectionId +
                '}';
    }
}
