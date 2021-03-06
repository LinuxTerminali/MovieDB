package com.terminali.moviedb.temp;

import android.graphics.Movie;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.List;

/**
 * Created by TERMINALi on 7/22/2016.
 */

public class SlideShowClient {
    private static final String API_base_URL="https://api.themoviedb.org/3/";
    private static final String API_key="ac86994ec3fa34236d4b5dee07f407f1";
    private static AsyncHttpClient client;
    List<Movie> movies;


    public SlideShowClient(){
        this.client=new AsyncHttpClient();
    }

    public static String  getAPIurl(String relativeUrl){
        return API_base_URL+relativeUrl;
    }


    public static  void getPoster(String url,String response,JsonHttpResponseHandler handler) {
       // String url = getAPIurl("movie/top_rated");
        RequestParams params = new RequestParams();
        params.put("api_key", API_key);
        params.put("append_to_response",response);
        client.get(API_base_URL+url, params, handler);

    }


}
