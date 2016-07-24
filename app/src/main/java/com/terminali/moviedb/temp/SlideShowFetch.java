package com.terminali.moviedb.temp;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

/**
 * Created by TERMINALi on 7/23/2016.
 */

public class SlideShowFetch {
    private static SlideShowClient client;
    private static String basePosterPath="http://image.tmdb.org/t/p/w342/";
    private static HashMap<String, String> movieList =  new HashMap<>();
    private static HashMap<String, String> tvList =  new HashMap<>();





    public HashMap<String, String> getMovieList(){
        movieList.put("The Shawshank Redemption","http://image.tmdb.org/t/p/w342//9O7gLzmreU0nGkIB6K3BsJbzvNv.jpg");
       movieList.put("Whiplash","http://image.tmdb.org/t/p/w342//lIv1QinFqz4dlp5U4lQ6HaiskOZ.jpg");
        movieList.put("The Godfather","http://image.tmdb.org/t/p/w342//d4KNaTrltq6bpkFS01pYtyXa09m.jpg");
        movieList.put("The Boy and the Beast","http://image.tmdb.org/t/p/w342//zCq2IrGlmPdOmEKR1giTeIg7ZdO.jpg");
        movieList.put("Interstellar","http://image.tmdb.org/t/p/w342//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg");
        return movieList;
    }




    public HashMap<String, String> getTvList(){
        tvList.put("Breaking Bad","http://image.tmdb.org/t/p/w342//1yeVJox3rjo2jBKrrihIMj7uoS9.jpg");
        tvList.put("Deutschland 83","http://image.tmdb.org/t/p/w342//yL7XdYrbjwPQg9Xt4NUspKLyM1K.jpg");
        tvList.put("Sherlock","http://image.tmdb.org/t/p/w342//vHXZGe5tz4fcrqki9ZANkJISVKg.jpg");
        tvList.put("Firefly","http://image.tmdb.org/t/p/w342//mWNadwBZIx8NyEw4smGftYtHHrE.jpg");
        tvList.put("Game of Thrones","http://image.tmdb.org/t/p/w342//jIhL6mlT7AblhbHJgEoiBIOUVl1.jpg");
        return tvList;
    }


    public void fetchMovie(){
        client = new SlideShowClient();

        client.getPoster("movie/top_rated",new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {
                JSONArray items = null;
                try{
                    items = responseBody.getJSONArray("results");

                    for(int i=0;i<5;i++){
                        responseBody= items.getJSONObject(i);



                        movieList.put(responseBody.getString("title"),basePosterPath+responseBody.getString("poster_path"));
                        Log.d("movie_name: ", basePosterPath+responseBody.getString("title"));
                        Log.d("images: ", basePosterPath+responseBody.getString("poster_path"));
                    }
                }catch (JSONException e){
                    Log.v("images","path",e);
                }
            }



        });

    }




    public void fetchTv(){
        client = new SlideShowClient();

        client.getPoster("tv/top_rated",new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {
                JSONArray item2 = null;
                try{
                    item2 = responseBody.getJSONArray("results");

                    for(int i=0;i<5;i++){
                        responseBody= item2.getJSONObject(i);



                       tvList.put(responseBody.getString("name"),basePosterPath+responseBody.getString("poster_path"));
                        Log.d("s_name: ", basePosterPath+responseBody.getString("name"));
                        Log.d("images: ", basePosterPath+responseBody.getString("poster_path"));
                    }
                }catch (JSONException e){
                    Log.v("images","path",e);
                }
            }



        });

    }



}
