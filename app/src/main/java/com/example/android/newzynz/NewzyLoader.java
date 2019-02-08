package com.example.android.newzynz;

/*
 * Grow With Google Challenge Scholarship: Android Basics
 * Project: 7
 * Version: 2.0
 * App Name: NewzyNews
 * Author: Joseph McDonald
 */

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

class NewzyLoader extends AsyncTaskLoader<List<Newzy>> {

    /**
     * Declare the newzy request URL
     */
    private final String newzyUrl;

    NewzyLoader(Context context, String url) {
        super(context);

        // Assign the newzy URl.
        newzyUrl = url;
    }

    @Override
    protected void onStartLoading() {

        forceLoad();
    }

    @Override
    public List<Newzy> loadInBackground() {

        // Don't perform the request if there are no URLs, or the first URL is null.
        if (newzyUrl == null) {
            return null;
        }
        return NewzyUtils.fetchNewzys(newzyUrl);
    }
}