package com.terminali.moviedb;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by TERMINALi on 7/19/2016.
 */

public class MovieDBClient {
    private final String API_key="ac86994ec3fa34236d4b5dee07f407f1";
    private final String API_base_URL="https://api.themoviedb.org/3/";
    private AsyncHttpClient client;

    public MovieDBClient(){

        this.client= new AsyncHttpClient();
    }
    private String getAPI_URL(String relativeUrl){

        return API_base_URL+relativeUrl;
    }

    public void getBOXofficeMovies(String url,int ram,JsonHttpResponseHandler handler){
        //String url =getAPI_URL("movie/upcoming");
        RequestParams params = new RequestParams();
        //Set<Integer> list = new HashSet<Integer>();
        params.put("api_key",API_key);
        params.put("page",ram);
        client.get(API_base_URL+url,params,handler);

    }


}
