package com.example.android.newzynz;

/*
 * Grow With Google Challenge Scholarship: Android Basics
 * Project: 7
 * Version: 2.0
 * App Name: NewzyNZ
 * Author: Joseph McDonald
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Newzy category selected.
    public static String newzysSection;

    // Declare the navigation drawer.
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find and setup the Toolbar.
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Find and setup the navigation drawer and open/close toggle.
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Find and setup NavigationView and its Listener.
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Launch default Newzy Fragment.
        launchNewzyFragment();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        // Select drawer destination Newzys.
        switch (item.getItemId()) {
            case R.id.nav_newzy:
                // General Newzys.
                newzysSection = null;
                break;

            case R.id.nav_books:
                // Books Newzys.
                newzysSection = item.getTitle().toString();
                break;

            case R.id.nav_film:
                // Film Newzys.
                newzysSection = item.getTitle().toString();
                break;

            case R.id.nav_music:
                // Music Newzys.
                newzysSection = item.getTitle().toString();
                break;

            case R.id.nav_sport:
                // Sport Newzys.
                newzysSection = item.getTitle().toString();
                break;

            case R.id.nav_travel:
                // Travel Newzys.
                newzysSection = item.getTitle().toString();
                break;
        }

        // If Settings destination chosen.
        if (item.getItemId() == R.id.nav_settings) {

            // Go to Newzy Settings.
            Intent settingsIntent = new Intent(this, NewzySettings.class);
            startActivity(settingsIntent);

        } else {
            // Launch Fragment with selected destination.
            launchNewzyFragment();
        }

        // Close the drawer after selecting a destination.
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private void launchNewzyFragment() {

        if (newzysSection == null) {
            setTitle(R.string.app_name);

        } else {
            setTitle(newzysSection);
        }
        // Go to the NewzyFragment with the chosen destination.
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout_content, new NewzyFragment()).commit();
    }

    @Override
    public void onBackPressed() {

        // If drawer is open, close it.
        if (drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();
        }
    }
}

