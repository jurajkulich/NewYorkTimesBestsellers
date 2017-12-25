package com.example.android.newyorktimesbestsellers;

/**
 * Created by Juraj on 12/25/2017.
 */

public class Book {
    String title;
    String author;
    String description;
    int rank;
    int rank_last_week;
    int weeks_on_list;
    String amazonLink;

    public Book(String title, String author, String description, int rank, int rank_last_week, int weeks_on_list, String amazonLink) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.rank = rank;
        this.rank_last_week = rank_last_week;
        this.weeks_on_list = weeks_on_list;
        this.amazonLink = amazonLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRank_last_week() {
        return rank_last_week;
    }

    public void setRank_last_week(int rank_last_week) {
        this.rank_last_week = rank_last_week;
    }

    public int getWeeks_on_list() {
        return weeks_on_list;
    }

    public void setWeeks_on_list(int weeks_on_list) {
        this.weeks_on_list = weeks_on_list;
    }

    public String getAmazonLink() {
        return amazonLink;
    }

    public void setAmazonLink(String amazonLink) {
        this.amazonLink = amazonLink;
    }
}
