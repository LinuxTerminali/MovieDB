package com.terminali.moviedb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
/**
 * Created by TERMINALi on 7/19/2016.
 */

public class BoxOfficeActivity extends AppCompatActivity {
    private final String LOG_TAG = MovieDBClient.class.getSimpleName();
    private ListView lvMovies;
    MovieDBClient client;
    private BoxOfficeMovieAdapter adapterMovies;

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_box_office);
        Toolbar myToolbar=(Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        lvMovies=(ListView)findViewById(R.id.lvMovies);
        ArrayList<BoxOfficeMovie> aMovies = new ArrayList<BoxOfficeMovie>();
        adapterMovies = new BoxOfficeMovieAdapter(this,aMovies);
        lvMovies.setAdapter(adapterMovies);
        fetchBoxofficeMovies();
    }

    private  void fetchBoxofficeMovies(){
        client = new MovieDBClient();
        client.getBOXofficeMovies(new JsonHttpResponseHandler(){

        @Override
        public void onSuccess(int statusCode, Header[] headers,JSONObject responseBody) {
            JSONArray items = null;
                try {
                // Get the movies json array
                items = responseBody.getJSONArray("results");
                // Parse json array into array of model objects
                ArrayList<BoxOfficeMovie> results = BoxOfficeMovie.fromJson(items);
                // Load model objects into the adapter
                for (BoxOfficeMovie movie : results) {
                    adapterMovies.add(movie); // add movie through the adapter
                }
                adapterMovies.notifyDataSetChanged();
            } catch (JSONException e) {
                Log.v("LOG_TAG","movie",e);
                e.printStackTrace();
            }
        }
    });
    }
}