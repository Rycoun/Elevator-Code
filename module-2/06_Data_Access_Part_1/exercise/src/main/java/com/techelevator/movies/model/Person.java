package com.techelevator.movies.model;

import java.time.LocalDate;

public class Person {

    private int id;
    private String name;
    private LocalDate birthday;
    private LocalDate deathDate;
    private String biography;
    private String profilePath;
    private String homePage;

    public Person() {
    }

    public Person(int id, String name, LocalDate birthday, LocalDate deathDate, String biography, String profilePath, String homePage) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.deathDate = deathDate;
        this.biography = biography;
        this.profilePath = profilePath;
        this.homePage = homePage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", deathDate=" + deathDate +
                ", biography='" + biography + '\'' +
                ", profilePath='" + profilePath + '\'' +
                ", homePage='" + homePage + '\'' +
                '}';
    }
}
