package com.terminali.moviedb.temp;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by TERMINALi on 7/24/2016.
 */

public class TomatoList {

    private String title;
    private int year;
    private String synopsis;
    private String posterUrl;
    private int criticsScore;
    private ArrayList<String> castList;

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public int getCriticsScore() {
        return criticsScore;
    }

    public String getCastList() {
        return TextUtils.join(", ", castList);
    }

public static TomatoList fromJson(JSONObject jsonObject){
        TomatoList t = new TomatoList();
        try {
            // Deserialize json into object fields
            t.title = jsonObject.getString("title");
            t.year = jsonObject.getInt("year");
            t.synopsis = jsonObject.getString("synopsis");
            t.posterUrl = jsonObject.getJSONObject("posters").getString("thumbnail");
            t.criticsScore = jsonObject.getJSONObject("ratings").getInt("critics_score");
            // Construct simple array of cast names
            t.castList = new ArrayList<String>();
            JSONArray abridgedCast = jsonObject.getJSONArray("abridged_cast");
            for (int i = 0; i < abridgedCast.length(); i++) {
                t.castList.add(abridgedCast.getJSONObject(i).getString("name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return t;
    }




    public static ArrayList<TomatoList> fromJson(JSONArray jsonArray){
        ArrayList<TomatoList> movies = new ArrayList<TomatoList>(jsonArray.length());
        // Process each result in json array, decode and convert to movie
        // object
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject moviesJson = null;
            try {
                moviesJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            TomatoList movie = TomatoList.fromJson(moviesJson);
            if (movie != null) {
                movies.add(movie);
            }
        }

        return movies;
    }

}
