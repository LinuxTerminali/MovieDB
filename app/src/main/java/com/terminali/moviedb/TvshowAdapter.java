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
 * Created by TERMINALi on 7/26/2016.
 */

public class TvshowAdapter extends ArrayAdapter<Tvshow> {
    private String basePosterPath="http://image.tmdb.org/t/p/w185/";
    public TvshowAdapter (Context context, ArrayList<Tvshow> poster){
        super(context,0,poster);

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Tvshow poster =getItem(position);
        String posterUrl=basePosterPath+poster.getPoster_path();
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
