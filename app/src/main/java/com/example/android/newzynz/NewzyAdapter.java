package com.example.android.newzynz;

/*
 * Grow With Google Challenge Scholarship: Android Basics
 * Project: 7
 * Version: 2.0
 * App Name: NewzyNews
 * Author: Joseph McDonald
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class NewzyAdapter extends RecyclerView.Adapter<NewzyHolder> {

    // Declare global variables.
    private final Context context;
    private final int itemResource;
    private final List<Newzy> newzys;

    NewzyAdapter(Context context, int itemResource, List<Newzy> newzys) {

        // Assign global variables with input arguments.
        this.context = context;
        this.itemResource = itemResource;
        this.newzys = newzys;
    }

    @NonNull
    @Override
    public NewzyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Inflate the item view and return the NewzyHolder.
        View view = LayoutInflater.from(parent.getContext())
                .inflate(this.itemResource, parent, false);

        return new NewzyHolder(this.context, view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewzyHolder holder, int position) {

        // Use position to access the current Newzy.
        Newzy newzy = this.newzys.get(position);

        // Bind the current Newzy to the holder.
        holder.bindPartner(newzy);
    }

    @Override
    public int getItemCount() {

        return this.newzys.size();
    }
}