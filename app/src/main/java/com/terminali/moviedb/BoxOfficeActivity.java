package com.terminali.moviedb;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

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

    MovieDBClient client;
    private TrailerAdapter adapterPoster;
    private String  basePosterPath ="http://image.tmdb.org/t/p/w500/";
    private TextView Movie_Title;
    private TextView releaseDate;
    private TextView Movie_language;
    private TextView Movie_popularity;
    private TextView vote_average;
    private TextView overView;
    private TextView voteCount;
    private TextView id;
    String url= "movie/";

    private ImageView poster_image;
    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_box_office);
        Toolbar myToolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        setupActionBar();

        Movie_Title=(TextView)findViewById(R.id.Movie_Title);
        Movie_language=(TextView)findViewById(R.id.Movie_language);
        releaseDate=(TextView)findViewById(R.id.releaseDate);
        Movie_popularity=(TextView)findViewById(R.id.Movie_popularity);
        vote_average=(TextView)findViewById(R.id.vote_average);
        overView=(TextView)findViewById(R.id.overView);
        voteCount=(TextView)findViewById(R.id.vote_count);
        id=(TextView)findViewById(R.id.Movie_id);

        poster_image=(ImageView) findViewById(R.id.poster_image);

        BoxOfficeMovie movie = (BoxOfficeMovie)
                getIntent().getSerializableExtra(TopMoviesFragment.Movie_Detail);
        fetchDetailView(movie);
    }



    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private  void fetchDetailView(BoxOfficeMovie movie){
        String posterURL= basePosterPath+movie.getPosterPath();
        Movie_Title.setText(movie.getTitle());
        Movie_language.setText(Html.fromHtml("<b>Language:</b> "+movie.getLanguage()));
        releaseDate.setText(Html.fromHtml("<b>Release Date:</b> "+movie.getReleaseDate()));
        Movie_popularity.setText(Html.fromHtml("<b>Popularity:</b> "+movie.getPopularity()));
        vote_average.setText(Html.fromHtml("<b>Average Votes:</b> "+movie.getVoteAverage()));
        voteCount.setText(Html.fromHtml("<b>Vote count:</b>"+movie.getVoteCount()));
        id.setText(Html.fromHtml("<b>Movie id:</b>"+movie.getMovie_id()));
        url=url+movie.getMovie_id()+"/videos";
        Picasso.with(this).
                load(posterURL)
                .error(R.drawable.small_movie_poster)
                .into(poster_image);
        overView.setText(Html.fromHtml("<b>Overview:</b> " + movie.getOverview()));
    }


    private void fetchTrailer() {

        client = new MovieDBClient();

            client.getBOXofficeMovies("movie/603/videos",1, new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {
                    JSONArray items = null;

                    try {
                        // Get the movies json array
                        items = responseBody.getJSONArray("results");
                        // Parse json array into array of model objects
                        ArrayList<MovieTrailer> results = MovieTrailer.fromJson(items);
                        // Load model objects into the adapter
                        for (MovieTrailer movie : results) {
                            adapterPoster.add(movie); // add movie through the adapter
                        }
                        adapterPoster.notifyDataSetChanged();
                    } catch (JSONException e) {
                        Log.v(LOG_TAG, "Movies with id ", e);
                        e.printStackTrace();
                    }
                }
            });
        }
    }


