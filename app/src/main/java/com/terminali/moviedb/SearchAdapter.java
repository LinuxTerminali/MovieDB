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
 * Created by TERMINALi on 7/28/2016.
 */

public class SearchAdapter extends ArrayAdapter<BoxOfficeMovie> {
    private String  basePosterPath ="http://image.tmdb.org/t/p/w500/";
    public SearchAdapter(Context context, ArrayList<BoxOfficeMovie> search){
        super(context,0,search);
    }


    @Override
    public View getView(int postition, View convertView, ViewGroup parent){
        BoxOfficeMovie results= getItem(postition);
        String actualurl= basePosterPath+results.getPosterPath();
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView= inflater.inflate(R.layout.item_search_result,parent,false);
        }
        TextView name_of=(TextView) convertView.findViewById(R.id.name_of);
        TextView language=(TextView) convertView.findViewById(R.id.tvLanguage);
        //TextView type=(TextView) convertView.findViewById(R.id.media_type);
        TextView vote_average=(TextView) convertView.findViewById(R.id.vote_average);
        ImageView poster= (ImageView) convertView.findViewById(R.id.search_image);

        name_of.setText(results.getTitle());
        language.setText("Language: "+results.getLanguage());
        vote_average.setText("Average votes: "+results.getVoteAverage());
        Picasso.with(getContext()).load(actualurl).into(poster);


        return convertView;

    }


}
