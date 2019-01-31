package com.example.android.newzynz;

/*
 * Grow With Google Challenge Scholarship: Android Basics
 * Project: 7
 * Version: 2.0
 * App Name: NewzyNews
 * Author: Joseph McDonald
 */

import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

public class NewzyFragment extends Fragment implements LoaderCallbacks<List<Newzy>> {

    // Newzy Loader ID constant.
    private static final int NEWZY_LOADER_ID = 0;

    // Declare the progress indicators.
    private ProgressBar progressCircle;
    private ImageView progressIcon;

    // Declare the newzy recycler view.
    private RecyclerView newzyRecyclerView;

    // Declare the empty state view.
    private TextView emptyStateView;

    // Declare the swipe refresh layout.
    private SwipeRefreshLayout swipeRefreshLayout;

    // Declare the newzy adapter.
    private NewzyAdapter newzyAdapter;

    public NewzyFragment() {
        // Required empty constructor.
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Find the in-progress views.
        progressCircle = Objects.requireNonNull(getActivity()).findViewById(R.id.progress_circle);
        progressIcon = getActivity().findViewById(R.id.progress_icon);

        // Find the empty state view.
        emptyStateView = getActivity().findViewById(R.id.empty_view);

        // Inflate the root newzy item view.
        final View rootView = inflater.inflate(R.layout.newzy_item_list, container, false);

        // Set the root newzy item view to the newzy recycler view.
        newzyRecyclerView = rootView.findViewById(R.id.newzy_recycler_view);

        // For performance, tell newzyRecyclerView its size is fixed.
        newzyRecyclerView.setHasFixedSize(true);

        // Declare and set the recycler view layout manager.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newzyRecyclerView.setLayoutManager(layoutManager);

        // Declare and set drawable item divider line.
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(getResources().getDrawable(R.drawable.divider));
        newzyRecyclerView.addItemDecoration(divider);

        // Find the swipe refresh layout and set color scheme.
        swipeRefreshLayout = rootView.findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));

        // Declare and set the OnRefreshListener to the swipe refresh layout.
        SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // Populate the Newzy list.
                getTheNewzys();
            }
        };
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);

        // Show the progress indicators.
        progressCircle.setVisibility(View.VISIBLE);
        progressIcon.setVisibility(View.VISIBLE);

        // Populate the Newzy list.
        getTheNewzys();

        return rootView;
    }

    private void getTheNewzys() {

        // Check Internet connectivity.
        ConnectivityManager cm = (ConnectivityManager) Objects.requireNonNull(getActivity()).
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = null;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();
        }

        // For easy network connectivity checking.
        boolean weHaveConnectivity = activeNetwork != null && activeNetwork.isConnected();

        if (weHaveConnectivity) {
            // Initialize the loader. Pass in the newzy ID constant defined above and pass in null
            // bundle. Pass in this fragment for the LoaderCallbacks parameter (which is valid
            // because this fragment implements the LoaderCallbacks interface).
            LoaderManager.getInstance(this).restartLoader(NEWZY_LOADER_ID, null, this);

        } else {
            // Hide the progress indicators.
            progressCircle.setVisibility(View.GONE);
            progressIcon.setVisibility(View.GONE);

            // Hide the Newzy list view.
            newzyRecyclerView.setVisibility(View.GONE);

            // Set "No internet" notification/instruction and show the empty state view.
            emptyStateView.setText(R.string.no_internet);
            emptyStateView.setVisibility(View.VISIBLE);

            // Stop the swipe refresh animation.
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @NonNull
    @Override
    public Loader<List<Newzy>> onCreateLoader(int i, Bundle bundle) {

        // Hide the empty state view.
        emptyStateView.setVisibility(View.GONE);

        final String DEFAULT_REQUEST_URL = getString(R.string.default_request_url);

        // Create and return the new loader for the given request URL.
        return new NewzyLoader(getContext(), buildRequestUrl(DEFAULT_REQUEST_URL));
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Newzy>> loader) {

        // Clear the newzy adapter.
        newzyAdapter = null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Newzy>> loader, List<Newzy> newzyData) {

        // Hide the progress indicators.
        progressCircle.setVisibility(View.GONE);
        progressIcon.setVisibility(View.GONE);

        // Clear the newzy adapter.
        newzyAdapter = null;

        // If there is a valid list of newzyData returned, then add it to the adapter's data set.
        if (newzyData != null && !newzyData.isEmpty()) {

            // Hide the empty state view.
            emptyStateView.setVisibility(View.GONE);

            // Show the Newzy RecyclerView.
            newzyRecyclerView.setVisibility(View.VISIBLE);

            // Assign the data to the adapter and RecyclerView.
            newzyAdapter = new NewzyAdapter(getActivity(), R.layout.newzy_item, newzyData);
            newzyRecyclerView.setAdapter(newzyAdapter);
            newzyAdapter.notifyDataSetChanged();

        } else {
            // Hide the Newzy recycler view.
            newzyRecyclerView.setVisibility(View.GONE);

            // Set "No Newzys" notification/instruction and show the empty state view.
            emptyStateView.setText(R.string.no_newzys);
            emptyStateView.setVisibility(View.VISIBLE);
        }
        // Stop the swipe refresh animation.
        swipeRefreshLayout.setRefreshing(false);
    }

    private String buildRequestUrl(String baseUrl) {

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());

        // Preferences string variables.
        String newzysSortBy = sp.getString(
                getString(R.string.settings_sort_newzys_by_key),
                getString(R.string.settings_sort_newzys_by_default));

        String newzysToRetrieve = sp.getString(
                getString(R.string.settings_newzys_to_retrieve_key),
                getString(R.string.settings_newzys_to_retrieve_default));

        String newzysFromDate = sp.getString(
                getString(R.string.settings_newzys_from_date_key),
                getString(R.string.settings_newzys_from_date_default));

        String newzysToDate = sp.getString(
                getString(R.string.settings_newzys_to_date_key),
                getString(R.string.settings_newzys_to_date_default));

        // Break apart the URI string that's passed into its parameter.
        Uri baseUri = Uri.parse(baseUrl);

        // Prepare the baseUri that we just parsed so we can add query parameters to it.
        Uri.Builder uriBuilder = baseUri.buildUpon();

        // If NOT null, append Newzys section parameter and its value.
        if (MainActivity.newzysSection != null) {
            uriBuilder.appendQueryParameter("section", MainActivity.newzysSection.toLowerCase());
        }

        // If NOT empty, append FROM DATE parameter and its value.
        if (!newzysFromDate.isEmpty()) {
            uriBuilder.appendQueryParameter("from-date", newzysFromDate);
        }

        // If NOT empty, append TO DATE parameter and its value.
        if (!newzysToDate.isEmpty()) {
            uriBuilder.appendQueryParameter("to-date", newzysToDate);
        }

        // Append query parameters.
        uriBuilder.appendQueryParameter("order-by", newzysSortBy);
        uriBuilder.appendQueryParameter("page-size", newzysToRetrieve);
        uriBuilder.appendQueryParameter("show-tags", getString(R.string.contributor));
        uriBuilder.appendQueryParameter("show-fields", getString(R.string.thumbnail));
        uriBuilder.appendQueryParameter("q", getString(R.string.new_zealand_query));
        uriBuilder.appendQueryParameter("api-key", BuildConfig.THE_GUARDIAN_API_KEY);

        return uriBuilder.toString();
    }
}