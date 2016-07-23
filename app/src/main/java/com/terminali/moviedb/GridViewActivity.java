package com.terminali.moviedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class GridViewActivity extends AppCompatActivity {
    private final String LOG_TAG = MovieDBClient.class.getSimpleName();
    MovieDBClient client;
    private GridAdapter adapterPoster;
    public static final String Movie_Detail = "movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view_activity);
        GridView grid_view = (GridView) findViewById(R.id.grid_view);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        setupActionBar();
        /*adapter*/

        ArrayList<BoxOfficeMovie> poster = new ArrayList<BoxOfficeMovie>();
        adapterPoster = new GridAdapter(this, poster);
        grid_view.setAdapter(adapterPoster);
        fetchBoxofficeMovies();
        grid_view.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent i = new Intent(GridViewActivity.this, BoxOfficeActivity.class);
                i.putExtra(Movie_Detail, adapterPoster.getItem(position));
                startActivity(i);
            }
        });
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void fetchBoxofficeMovies() {

        client = new MovieDBClient();
        for (int i = 10; i >= 1; i--) {

            client.getBOXofficeMovies(i, new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {
                    JSONArray items = null;

                    try {
                        // Get the movies json array
                        items = responseBody.getJSONArray("results");
                        // Parse json array into array of model objects
                        ArrayList<BoxOfficeMovie> results = BoxOfficeMovie.fromJson(items);
                        // Load model objects into the adapter
                        for (BoxOfficeMovie movie : results) {
                            adapterPoster.add(movie); // add movie through the adapter
                        }
                        adapterPoster.notifyDataSetChanged();
                    } catch (JSONException e) {
                        Log.v("LOG_TAG", "movie", e);
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}