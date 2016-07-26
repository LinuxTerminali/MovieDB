package com.terminali.moviedb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by TERMINALi on 7/26/2016.
 */

public class TrailerAdapter extends ArrayAdapter<MovieTrailer> {
    private String basePosterPath = "http://image.tmdb.org/t/p/w185/";

    public TrailerAdapter(Context context, ArrayList<MovieTrailer> poster) {
        super(context, 0, poster);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieTrailer poster = getItem(position);
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.poster_box_office, parent, false);
        }
        TextView text= (TextView) convertView.findViewById(R.id.temp);
        TextView text2=(TextView) convertView.findViewById(R.id.temp);
        text.setText(poster.getKey());
        text.setText(poster.getName());


        return convertView;
    }


}
