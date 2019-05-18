package com.example.android.newzynz;

/*
 * Grow With Google Challenge Scholarship: Android Basics
 * Project: 7
 * Version: 2.0
 * App Name: NewzyNZ
 * Author: Joseph McDonald
 */

class Newzy {

    /**
     * Newzy section.
     */
    private final String newzySection;

    /**
     * Newzy publication date.
     */
    private final String newzyDate;

    /**
     * Newzy title.
     */
    private final String newzyTitle;

    /**
     * Newzy web URL.
     */
    private final String newzyUrl;

    /**
     * Newzy author.
     */
    private final String newzyAuthor;

    /**
     * Newzy image URL.
     */
    private final String newzyImage;

    /**
     * Create a Newzy object without time.
     *
     * @param section Newzy section.
     * @param date    Newzy publication date.
     * @param title   Newzy publication time.
     * @param url     Newzy title.
     * @param author  Newzy web URL.
     * @param image   Newzy image URL.
     */
    Newzy(String section, String date, String title, String url, String author, String image) {
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
    String getNewzySection() {
        return newzySection;
    }

    /**
     * Get the Newzy publication date.
     */
    String getNewzyDate() {
        return newzyDate;
    }

    /**
     * Get the Newzy title.
     */
    String getNewzyTitle() {
        return newzyTitle;
    }

    /**
     * Get the Newzy web URL.
     */
    String getNewzyUrl() {
        return newzyUrl;
    }

    /**
     * Get the Newzy author.
     */
    String getNewzyAuthor() {
        return newzyAuthor;
    }

    /**
     * Get the Newzy image URL.
     */
    String getNewzyImage() {
        return newzyImage;
    }
}