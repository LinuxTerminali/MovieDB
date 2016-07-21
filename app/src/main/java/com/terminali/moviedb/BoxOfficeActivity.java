package com.terminali.moviedb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by TERMINALi on 7/19/2016.
 */

public class BoxOfficeActivity extends AppCompatActivity {
    private String  basePosterPath ="http://image.tmdb.org/t/p/w185/";
    private TextView Movie_Title;
    private TextView releaseDate;
    private TextView Movie_language;
    private TextView Movie_popularity;
    private TextView vote_average;
    private TextView overView;
    private TextView voteCount;

    private ImageView poster_image;
    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_box_office);
        Toolbar myToolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        Movie_Title=(TextView)findViewById(R.id.Movie_Title);
        Movie_language=(TextView)findViewById(R.id.Movie_language);
        releaseDate=(TextView)findViewById(R.id.releaseDate);
        Movie_popularity=(TextView)findViewById(R.id.Movie_popularity);
        vote_average=(TextView)findViewById(R.id.vote_average);
        overView=(TextView)findViewById(R.id.overView);
        voteCount=(TextView)findViewById(R.id.vote_count);

        poster_image=(ImageView) findViewById(R.id.poster_image);

        BoxOfficeMovie movie = (BoxOfficeMovie)
                getIntent().getSerializableExtra(GridViewActivity.Movie_Detail);
        fetchDetailView(movie);
    }

    private  void fetchDetailView(BoxOfficeMovie movie){
        String posterURL= basePosterPath+movie.getPosterPath();
        Movie_Title.setText(movie.getTitle());
        Movie_language.setText(Html.fromHtml("<b>Language:</b> "+movie.getLanguage()));
        releaseDate.setText(Html.fromHtml("<b>Release Date:</b> "+movie.getReleaseDate()));
        Movie_popularity.setText(Html.fromHtml("<b>Popularity:</b> "+movie.getPopularity()));
        vote_average.setText(Html.fromHtml("<b>Average Votes:</b> "+movie.getVoteAverage()));
        voteCount.setText(Html.fromHtml("<b>Vote count:</b>"+movie.getVoteCount()));
        Picasso.with(this).
                load(posterURL)
                .error(R.drawable.small_movie_poster)
                .into(poster_image);
        overView.setText(Html.fromHtml("<b>Overview:</b> " + movie.getOverview()));
    }
}