package com.example.android.newzynz;

/*
 * Grow With Google Challenge Scholarship: Android Basics
 * Project: 7
 * Version: 2.0
 * App Name: NewzyNews
 * Author: Joseph McDonald
 */

public class Newzy {

    /**
     * Newzy section.
     */
    private String newzySection;

    /**
     * Newzy publication date.
     */
    private String newzyDate;

    /**
     * Newzy title.
     */
    private String newzyTitle;

    /**
     * Newzy web URL.
     */
    private String newzyUrl;

    /**
     * Newzy author.
     */
    private String newzyAuthor;

    /**
     * Newzy image URL.
     */
    private String newzyImage;

    /**
     * Create a Newzy object without time.
     *
     * @param section Newzy section.
     * @param date Newzy publication date.
     * @param title Newzy publication time.
     * @param url Newzy title.
     * @param author Newzy web URL.
     * @param image Newzy image URL.
     */
    public Newzy(String section, String date, String title, String url, String author, String image) {
        newzySection = section;
        newzyDate = date;
        newzyTitle = title;
        newzyUrl = url;
        newzyAuthor = author;
        newzyImage = image;
    }

    /**
     * Get the Newzy section.
     */
    public String getNewzySection() {
        return newzySection;
    }

    /**
     * Get the Newzy publication date.
     */
    public String getNewzyDate() {
        return newzyDate;
    }

    /**
     * Get the Newzy title.
     */
    public String getNewzyTitle() {
        return newzyTitle;
    }

    /**
     * Get the Newzy web URL.
     */
    public String getNewzyUrl() {
        return newzyUrl;
    }

    /**
     * Get the Newzy author.
     */
    public String getNewzyAuthor() {
        return newzyAuthor;
    }

    /**
     * Get the Newzy image URL.
     */
    public String getNewzyImage() {
        return newzyImage;
    }
}