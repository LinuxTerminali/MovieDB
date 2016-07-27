package com.terminali.moviedb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by TERMINALi on 7/24/2016.
 */

public class Tvshow implements Serializable {
    private  String name;
    private String language;
    private String air_date;
    private String origin_Country;
    private String overView;
    private String poster_path;


    private int voteCount;
    private int show_id;

    private double vote_average;
    private double popularity;

    public String getName(){
        return name;
    }

    public String getLanguage(){
        return language;
    }

    public  String getAir_date(){
        return air_date;
    }


    public String getOrigin_Country(){
        return origin_Country;
    }

    public String getOverView(){
        return overView;
    }

    public String getPoster_path(){
        return poster_path;
    }


    public double getPopularity(){
        return popularity;
    }

    public int getVoteCount(){
        return voteCount;
    }

    public double getVote_average(){
        return vote_average;
    }

    public  int getShow_id(){
       return  show_id;
    }


    public static Tvshow fromJson(JSONObject jsonObject){
        Tvshow t =new Tvshow();
        try{
            t.name=jsonObject.getString("name");
            t.air_date=jsonObject.getString("first_air_date");
            t.language=jsonObject.getString("original_language");
            t.origin_Country=jsonObject.getString("origin_country");

            t.poster_path=jsonObject.getString("poster_path");

            t.popularity=jsonObject.getDouble("popularity");
            t.vote_average=jsonObject.getDouble("vote_average");
            t.voteCount=jsonObject.getInt("vote_count");
            t.show_id=jsonObject.getInt("id");
            t.overView=jsonObject.getString("overview");
        }catch(JSONException e){
            e.printStackTrace();
            return null;
        }
        return t;
    }



    public static ArrayList<Tvshow> fromJson(JSONArray jsonArray){
        ArrayList<Tvshow> results= new ArrayList<Tvshow>(jsonArray.length());
        for(int i=0;i<=jsonArray.length();i++){
            JSONObject shows= null;
            try{
                shows=jsonArray.getJSONObject(i);
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
            Tvshow showslist =Tvshow.fromJson(shows);
            if(results!=null){
                results.add(showslist);
            }
        }
        return  results;
        }
    }



