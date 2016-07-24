package com.terminali.moviedb;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by TERMINALi on 7/24/2016.
 */

public class RottenClient {
    private final String API_KEY="9htuhtcb4ymusd73d4z6jxcj";
    private final String API_BASE_URL="http://api.rottentomatoes.com/api/public/v1.0/";
    private AsyncHttpClient client;


    public RottenClient(){

        this.client=new  AsyncHttpClient();
    }

    private String getAPiUrl(String relativeURL){
        return API_BASE_URL+relativeURL;
    }


    public void getMovies(JsonHttpResponseHandler handler){
        String url=  getAPiUrl("lists/movies/box_office.json");
        RequestParams params = new RequestParams("apikey",API_KEY);
        client.get(url,params,handler);
    }


}
