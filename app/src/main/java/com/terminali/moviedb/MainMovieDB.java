package com.terminali.moviedb;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.terminali.moviedb.MovieGenreFragments.GenreMovieFragment;
import com.terminali.moviedb.TVGenreFragment.TvGenreFragment;


public class MainMovieDB extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ConnectivityReceiver.ConnectivityReceiverListener {
    private final static String TAG = "TestActivity";

    FragmentManager mfragmentManager;
    FragmentTransaction mfragmentTransaction;
    public static final String querydetail = "show";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_slide_show_main);
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        checkConnection();
        mfragmentManager= getSupportFragmentManager();
        mfragmentTransaction= mfragmentManager.beginTransaction();
        mfragmentTransaction.replace(R.id.containerView,new TopHomeFragment()).commit();


        Log.d(TAG, "On create .....");


    }
        /*view pager adapter */

        //movieslider();
        //tvSlider();

    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }

    // Showing the status in Snackbar
    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (!isConnected) {
            message = "Sorry! Not connected to internet";
            color = Color.RED;
            Snackbar snackbar = Snackbar
                    .make(findViewById(R.id.fab), message, Snackbar.LENGTH_LONG);

            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(color);
            snackbar.show();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();

        // register connection status listener
        ConnectivityCheck.getInstance().setConnectivityListener(this);
    }
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.slide_show_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id==R.id.search) {
            final MenuItem searchItem = item;
            final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                    intent.putExtra(querydetail,query);
                    startActivity(intent);
                    return true;
                }


                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }
            });
        }

        return super.onOptionsItemSelected(item);
    }






    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            FragmentTransaction fragmentTransaction= mfragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerView,new Movies()).commit();

        } else if (id == R.id.Genre) {
            FragmentTransaction fragmentTransaction= mfragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerView,new GenreMovieFragment()).commit();

        } else if (id == R.id.nav_share) {
            FragmentTransaction fragmentTransaction= mfragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerView,new TvShowFragment()).commit();

        } else if (id == R.id.nav_send) {
            FragmentTransaction fragmentTransaction= mfragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerView,new TvGenreFragment()).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }



}