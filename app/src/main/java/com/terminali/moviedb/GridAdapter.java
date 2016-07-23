package com.terminali.moviedb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by TERMINALi on 7/21/2016.
 */

public class GridAdapter extends ArrayAdapter<BoxOfficeMovie> {
    private String basePosterPath="http://image.tmdb.org/t/p/w185/";
    public GridAdapter (Context context, ArrayList<BoxOfficeMovie> poster){
        super(context,0,poster);

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        BoxOfficeMovie poster =getItem(position);
        String posterUrl=basePosterPath+poster.getPosterPath();
        if(convertView==null){
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.poster_box_office,parent, false);
        }
        ImageView poster_image=(ImageView) convertView.findViewById(R.id.poster_image);
        Picasso.with(getContext())
                .load(posterUrl)
                .error(R.drawable.small_movie_poster)
                .into(poster_image);
        return convertView;
    }


}
