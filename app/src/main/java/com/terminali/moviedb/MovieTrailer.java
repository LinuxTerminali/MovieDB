package com.terminali.moviedb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by TERMINALi on 7/26/2016.
 */

public class MovieTrailer {

    private String key;
    private  String name;

    public String getKey(){
        return key;
    }
    public String getName(){
        return name;
    }


    public static MovieTrailer fromJson(JSONObject jsonObject){
        MovieTrailer b =new MovieTrailer();
        try{
            b.key=jsonObject.getString("key");
            b.key=jsonObject.getString("name");
        }catch(JSONException e){
            e.printStackTrace();
            return null;
        }
        return b;
    }

    public static ArrayList<MovieTrailer> fromJson(JSONArray jsonArray){
        ArrayList<MovieTrailer> results = new ArrayList<MovieTrailer>(jsonArray.length());
        for(int i=0; i<=jsonArray.length();i++){
            JSONObject resultsJson =null;
            try{
                resultsJson=jsonArray.getJSONObject(i);
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
            MovieTrailer trailer =MovieTrailer.fromJson(resultsJson);
            if(results!=null){
                results.add(trailer);
            }
        }
        return  results;
    }

}

