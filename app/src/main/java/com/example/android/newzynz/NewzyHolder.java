package com.example.android.newzynz;

/*
 * Grow With Google Challenge Scholarship: Android Basics
 * Project: 7
 * Version: 2.0
 * App Name: NewzyNews
 * Author: Joseph McDonald
 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.regex.Pattern;

public class NewzyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final Context context;

    // Declare the current Newzy.
    private Newzy currentNewzy;

    // Declare the current Newzy content views.
    private final TextView newzySectionTextView;
    private final TextView newzyTitleTextView;
    private final TextView newzyAuthorTextView;
    private final TextView newzyDateTextView;
    private final ImageView newzyImageView;

    NewzyHolder(Context context, View newzyView) {

        super(newzyView);

        // Set the context.
        this.context = context;

        // Find and assign the Newzy subviews.
        newzySectionTextView = newzyView.findViewById(R.id.newzy_section_text_view);
        newzyTitleTextView = newzyView.findViewById(R.id.newzy_title_text_view);
        newzyAuthorTextView = newzyView.findViewById(R.id.newzy_author_text_view);
        newzyDateTextView = newzyView.findViewById(R.id.newzy_date_text_view);
        newzyImageView = newzyView.findViewById(R.id.newzy_image_view);

        // Set the "onClick" listener to the Newzy view.
        newzyView.setOnClickListener(this);
    }

    public void bindPartner(Newzy newzy) {

        // Assign the current Newzy.
        currentNewzy = newzy;

        // Set the Newzy text views.
        newzySectionTextView.setText(currentNewzy.getNewzySection());
        newzyTitleTextView.setText(currentNewzy.getNewzyTitle());
        newzyAuthorTextView.setText(currentNewzy.getNewzyAuthor());
        newzyDateTextView.setText(formatDate(currentNewzy.getNewzyDate()));

        // If a Newzy image URL exists, get and set the image.
        if (currentNewzy.getNewzyImage() != null) {

            int IMAGE_TARGET_WIDTH = 500;
            int IMAGE_TARGET_HEIGHT = 300;

            Picasso.get().load(currentNewzy.getNewzyImage())
                    .resize(IMAGE_TARGET_WIDTH, IMAGE_TARGET_HEIGHT)
                    .error(R.drawable.newzy)
                    .into(newzyImageView);

        } else {
            newzyImageView.setImageResource(R.drawable.newzy);
        }
    }

    @Override
    public void onClick(View v) {

        // Create the toast message and show it.
        Toast.makeText(context, context.getString(R.string.loading_url), Toast.LENGTH_SHORT).show();

        // Create intent for the current Newzy URL and start it.
        Intent urlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentNewzy.getNewzyUrl()));
        context.startActivity(urlIntent);
    }

    private String formatDate(String dateAndTime) {

        // Parse the date from the "dateAndTime".
        String[] parts = dateAndTime.split(Pattern.quote(context.getString(R.string.date_separator)));
        return parts[0];
    }
}