package com.terminali.moviedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;



public class TopMoviesFragment extends Fragment {

    private final String LOG_TAG = MovieDBClient.class.getSimpleName();

    MovieDBClient client;
    private GridAdapter adapterPoster;
    public static final String Movie_Detail = "movie";

    public TopMoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

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

            client.getBOXofficeMovies("movie/top_rated",i, new JsonHttpResponseHandler() {

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
                        Toast.makeText(getActivity(), "Loading...", Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        Log.v("LOG_TAG", "movie", e);
                        e.printStackTrace();
                    }
                }
            });
        }
    }


}
