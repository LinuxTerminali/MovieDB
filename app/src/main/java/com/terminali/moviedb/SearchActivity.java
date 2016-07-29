package com.terminali.moviedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.terminali.moviedb.MainMovieDB.querydetail;

public class SearchActivity extends AppCompatActivity {
    private final static String TAG = "TestActivity";
    public static final String Movie_Detail = "movie";
    private SearchAdapter searchAdapter;
    String value;
    SearchClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView listView=(ListView)findViewById(R.id.list_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        value= intent.getStringExtra(querydetail);
        Log.d("Search_query",value);



        ArrayList<BoxOfficeMovie> searchResults= new ArrayList<BoxOfficeMovie>();
        searchAdapter= new SearchAdapter(this,searchResults);
        listView.setAdapter(searchAdapter);
        fetchSearchData(value);
        setTitle(value);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent i = new Intent(getApplicationContext(), BoxOfficeActivity.class);
                i.putExtra(Movie_Detail, searchAdapter.getItem(position));
                startActivity(i);
            }
        });
    }


        public void fetchSearchData(String value){
            client = new SearchClient();

            Log.d("fetchresultquery: ",value );
            for(int i=1;i<=5;i++) {
                client.getSearch(value, i, new JsonHttpResponseHandler() {

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
                                searchAdapter.add(movie);
                                // add movie through the adapter
                            }
                            searchAdapter.notifyDataSetChanged();
                            for (int i = 0; i < items.length(); i++) {
                                responseBody = items.getJSONObject(i);
                                //Log.d("implements", responseBody.getString("poster_path"));
                                Log.d("implement", "movie" + responseBody.getString("title"));
                                //Log.d("implement",responseBody.getString("name"));
                            }
                            for (int i = 0; i < items.length(); i++) {
                                responseBody = items.getJSONObject(i);
                                //Log.d("implements", responseBody.getString("poster_path"));
                                //Log.d("implement",responseBody.getString("title"));
                                Log.d("implement", "tvshow " + responseBody.getString("name"));
                            }
                        } catch (JSONException e) {
                            Log.v("LOG_TAG", "search", e);
                            e.printStackTrace();
                        }
                    }
                });
            }

        }


}