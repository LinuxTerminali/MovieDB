package com.terminali.moviedb.temp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.terminali.moviedb.BoxOfficeMovie;
import com.terminali.moviedb.R;

import java.util.ArrayList;


/**
 * Created by TERMINALi on 7/22/2016.
 */

public class SlideShowAdapter extends ArrayAdapter<BoxOfficeMovie>{
    private String basePosterPath="http://image.tmdb.org/t/p/w185/";

    public SlideShowAdapter(Context context, ArrayList<BoxOfficeMovie> toprated){
        super(context,0,toprated);
}

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        BoxOfficeMovie movie = getItem(position);
        String posterUrl=basePosterPath+movie.getPosterPath();
        if(convertView==null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.slider_basic_list, parent, false);
            }

        TextView mTitle=(TextView) convertView.findViewById(R.id.mTitle);
        TextView mCriticsScore=(TextView) convertView.findViewById(R.id.mCriticsScore);
        TextView mCast= (TextView) convertView.findViewById(R.id.mCast);
        ImageView ivImage= (ImageView) convertView.findViewById(R.id.ivImage);

        mTitle.setText(movie.getTitle());
        mCriticsScore.setText("Popularity: "+movie.getPopularity());
        mCast.setText(movie.getReleaseDate());

        Picasso.with(getContext())
                .load(posterUrl)
                .into(ivImage);
        return convertView;

    }

}
