package com.terminali.moviedb;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by TERMINALi on 7/28/2016.
 */

public class SearchModels implements Serializable {

    private String title;

    private String name;
    private String language;
    private String media_type;


    private String posterPath;

    private double voteAverage;



    public String getSearchTitle(){
        return title;
    }


    public String getSearchLanguage(){

        return language;
    }



    public double getSearchVoteAverage(){
        return voteAverage;
    }

    public String getSearchPosterPath(){
        return posterPath;
    }



    public String getSearchName(){
        return name;
    }

    public String getSearchMedia_type(){

        return  media_type;
    }

    public static SearchModels fromJson(JSONObject jsonObject){
        SearchModels s =new SearchModels();
        try{
            s.title=jsonObject.getString("title");
            s.language=jsonObject.getString("original_language");

            s.posterPath=jsonObject.getString("poster_path");

            s.voteAverage=jsonObject.getDouble("vote_average");
            s.name=jsonObject.getString("name");
            s.media_type=jsonObject.getString("media_type");
        }catch(JSONException e){
            Log.d("models", "exception",e);
            e.printStackTrace();
            return null;
        }
        return s;
    }

    public static ArrayList<SearchModels> fromJson(JSONArray jsonArray){
        ArrayList<SearchModels> results = new ArrayList<SearchModels>(jsonArray.length());
        for(int i=0; i<jsonArray.length();i++){
            JSONObject resultsJson =null;
            try{
                resultsJson=jsonArray.getJSONObject(i);

            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
            SearchModels movie=SearchModels.fromJson(resultsJson);
            if(results!=null){

                results.add(movie);
            }
        }
        return  results;
    }

}

