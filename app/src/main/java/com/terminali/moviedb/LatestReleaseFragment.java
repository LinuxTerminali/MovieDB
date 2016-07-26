package com.terminali.moviedb;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by TERMINALi on 7/24/2016.
 */

public class LatestReleaseFragment extends Fragment {
    private final String LOG_TAG = MovieDBClient.class.getSimpleName();

    MovieDBClient client;
    private GridAdapter adapterPoster;
    public static final String Movie_Detail = "movie";


    public LatestReleaseFragment(){

    }


    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_movies_fragment, container, false);
        GridView grid_view = (GridView) view.findViewById(R.id.grid_view);
        ArrayList<BoxOfficeMovie> poster = new ArrayList<BoxOfficeMovie>();
        adapterPoster = new GridAdapter(getActivity(),poster);
        grid_view.setAdapter(adapterPoster);
        fetchBoxofficeMovies();
        grid_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent i = new Intent(getActivity(), BoxOfficeActivity.class);
                i.putExtra(Movie_Detail, adapterPoster.getItem(position));
                startActivity(i);
            }
        });
        return view;
    }


    private void fetchBoxofficeMovies() {

        client = new MovieDBClient();
        for (int i = 10; i >= 1; i--) {

            client.getBOXofficeMovies("movie/now_playing",i, new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {
                    JSONArray items = null;

                    try {
                        // Get the movies json array
                        items = responseBody.getJSONArray("results");
                        // Parse json array into array of model objects
                        ArrayList<BoxOfficeMovie> results = BoxOfficeMovie.fromJson(items);
                        // Load model objects into the adapter
                        for (BoxOfficeMovie movie : results) {
                            adapterPoster.add(movie); // add movie through the adapter
                        }
                        adapterPoster.notifyDataSetChanged();
                    } catch (JSONException e) {
                        Log.v("LOG_TAG", "movie", e);
                        e.printStackTrace();
                    }
                }
            });
        }
    }


}
