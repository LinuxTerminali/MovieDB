package com.terminali.moviedb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by TERMINALi on 7/19/2016.
 */

public class BoxOfficeMovie {
    private String title;
    private String language;
    private String releaseDate;

    private String posterPath;

    private double popularity;
    private int voteCount;
    private double voteAverage;

    public String getTitle(){
        return title;
    }

    public String getReleaseDate()
    {
         return releaseDate;
     }

    public String getLanguage(){
        return language;
    }


    public double getPopularity(){
        return popularity;
    }

    public double getVoteAverage(){
        return voteAverage;
    }

    public int getVoteCount(){
        return voteCount;
    }

    public String getPosterPath(){
        return posterPath;
    }

    public static BoxOfficeMovie fromJson(JSONObject jsonObject){
        BoxOfficeMovie b =new BoxOfficeMovie();
        try{
            b.title=jsonObject.getString("title");
            b.releaseDate=jsonObject.getString("release_date");
            b.language=jsonObject.getString("original_language");

            b.posterPath=jsonObject.getString("poster_path");

            b.popularity=jsonObject.getDouble("popularity");
            b.voteAverage=jsonObject.getDouble("vote_average");
            b.voteCount=jsonObject.getInt("vote_count");
        }catch(JSONException e){
            e.printStackTrace();
            return null;
        }
        return b;
    }

    public static ArrayList<BoxOfficeMovie> fromJson(JSONArray jsonArray){
            ArrayList<BoxOfficeMovie> results = new ArrayList<BoxOfficeMovie>(jsonArray.length());
            for(int i=0; i<=jsonArray.length();i++){
                JSONObject resultsJson =null;
                try{
                    resultsJson=jsonArray.getJSONObject(i);
                }catch (Exception e){
                    e.printStackTrace();
                    continue;
                }
                BoxOfficeMovie movie =BoxOfficeMovie.fromJson(resultsJson);
                if(results!=null){
                    results.add(movie);
                }
            }
        return  results;
    }

}
