package com.terminali.moviedb;

import android.graphics.Movie;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.List;

/**
 * Created by TERMINALi on 7/22/2016.
 */

public class TempClass {
    private static final String API_base_URL="https://api.themoviedb.org/3/";
    private static final String API_key="ac86994ec3fa34236d4b5dee07f407f1";
    private static AsyncHttpClient client;
    List<Movie> movies;


    public  TempClass(){
        this.client=new AsyncHttpClient();
    }

    public static String  getAPIurl(String relativeUrl){
        return API_base_URL+relativeUrl;
    }


    public static  void getPoster(String url,JsonHttpResponseHandler handler) {
       // String url = getAPIurl("movie/top_rated");
        RequestParams params = new RequestParams();
        params.put("api_key", API_key);
        client.get(API_base_URL+url, params, handler);

    }


}
