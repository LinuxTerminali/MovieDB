package com.terminali.moviedb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by TERMINALi on 7/19/2016.
 */

public class BoxOfficeMovieAdapter extends ArrayAdapter<BoxOfficeMovie> {
    private String  basePosterPath ="http://image.tmdb.org/t/p/w185/";
    public BoxOfficeMovieAdapter(Context context, ArrayList<BoxOfficeMovie> aMovies){
        super(context,0,aMovies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        BoxOfficeMovie movie =getItem(position);
        String posterURL= basePosterPath+movie.getPosterPath();

        if(convertView==null){
            LayoutInflater inflater =LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.item_box_office_movie,parent,false);
        }
        TextView Movie_Title=(TextView)convertView.findViewById(R.id.Movie_Title);
        TextView Movie_language=(TextView)convertView.findViewById(R.id.Movie_language);
        TextView releaseDate=(TextView) convertView.findViewById(R.id.releaseDate);
        TextView Movie_popularity=(TextView) convertView.findViewById(R.id.Movie_popularity);
        TextView vote_average=(TextView) convertView.findViewById(R.id.vote_average);
        ImageView poster_image=(ImageView) convertView.findViewById(R.id.poster_image);

        Movie_Title.setText(movie.getTitle());
        Movie_language.setText("Language: "+movie.getLanguage());
        releaseDate.setText("Release Date: "+movie.getReleaseDate());
        Movie_popularity.setText("Popularity: "+movie.getPopularity());
        vote_average.setText("Average Votes: "+movie.getVoteAverage());
        Picasso.with(getContext()).load(posterURL).into(poster_image);
        return convertView;

    }
}
