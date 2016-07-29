package com.terminali.moviedb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;
/**
 * Created by TERMINALi on 7/26/2016.
 */

public class TVShowDetail extends AppCompatActivity {
    private final String LOG_TAG = MovieDBClient.class.getSimpleName();
    private static final String Movie_SHARE_HASHTAG = " #EDB";
    MovieDBClient client;
    private String  basePosterPath ="http://image.tmdb.org/t/p/w500/";
    private TextView Movie_Title;
    private TextView releaseDate;
    private TextView Movie_language;
    private TextView Movie_popularity;
    private TextView vote_average;
    private TextView overView;
    private TextView voteCount;
    private TextView id;
    private TextView origin;

    String url= "tv/";
    String youtube="youtube";
    Tvshow show;
    String thumb="default";

    private ImageView poster_image;
    private  ImageView thumbnail;
    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.tv_show_detail);
        Toolbar myToolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        setupActionBar();

        Movie_Title=(TextView)findViewById(R.id.Movie_Title);
        Movie_language=(TextView)findViewById(R.id.Movie_language);
        origin=(TextView)findViewById(R.id.Origin_Country);
        releaseDate=(TextView)findViewById(R.id.releaseDate);
        Movie_popularity=(TextView)findViewById(R.id.Movie_popularity);
        vote_average=(TextView)findViewById(R.id.vote_average);
        overView=(TextView)findViewById(R.id.overView);
        voteCount=(TextView)findViewById(R.id.vote_count);
        id=(TextView)findViewById(R.id.Movie_id);
        thumbnail=(ImageView) findViewById(R.id.thumbnail);

        poster_image=(ImageView) findViewById(R.id.poster_image);

        show = (Tvshow)
                getIntent().getSerializableExtra(TopTvshowFragment.Show_Detail);
        url=url+show.getShow_id()+"/videos";
        setTitle(show.getName());

        try{
            fetchTrailer();
        }catch(Exception e){
            Log.d("function","exception",e);
        }

        fetchDetailView(show);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail_movie, menu);

        // Retrieve the share menu item
        MenuItem menuItem = menu.findItem(R.id.action_share);

        // Get the provider and hold onto it to set/change the share intent.
        ShareActionProvider mShareActionProvider=(ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        // Attach an intent to this ShareActionProvider.  You can update this at any time,
        // like when the user selects a new piece of data they might like to share.
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(createShareForecastIntent());
        } else {
            Log.d(LOG_TAG, "Share Action Provider is null?");
        }
        return true;
    }

    private Intent createShareForecastIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,Html.fromHtml("<b>TV Show:</b>  ")+show.getName()+Html.fromHtml("<br>")+Html.fromHtml(" <b>OverView:</b> ")+show.getOverView()+Html.fromHtml("<br>")+Html.fromHtml("  <b>Find More info like this on</b>  ")+Movie_SHARE_HASHTAG);

        return shareIntent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            createShareForecastIntent();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    public String getYoutube(){
        return youtube;
    }

    private  void fetchDetailView(Tvshow show){
        String posterURL= basePosterPath+show.getPoster_path();
        Movie_Title.setText(show.getName());
        Movie_language.setText(Html.fromHtml("<b>Language:</b> "+show.getLanguage()));
        //origin.setText(Html.fromHtml("<b>Origin Country</b>"+show.getOrigin_Country()));
        releaseDate.setText(Html.fromHtml("<b>First Air Date:</b> "+show.getAir_date()));
        Movie_popularity.setText(Html.fromHtml("<b>Popularity:</b> "+show.getPopularity()));
        vote_average.setText(Html.fromHtml("<b>Average Votes:</b> "+show.getVote_average()));
        voteCount.setText(Html.fromHtml("<b>Vote count:</b>"+show.getVoteCount()));

        //Log.v(LOG_TAG,youtube);
        Picasso.with(this).
                load(posterURL)
                .error(R.drawable.small_movie_poster)
                .into(poster_image);
        overView.setText(Html.fromHtml("<b>Overview:</b> " + show.getOverView()));

    }


   private void fetchTrailer() {

        client = new MovieDBClient();
        Log.v(LOG_TAG,url);

        client.getBOXofficeMovies(url,1, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {
                JSONArray item1 = null;
                //JSONArray item2 =null;
                String array="";



                try {
                    item1=responseBody.getJSONArray("results");
                    for(int i=0;i<item1.length();i++) {
                        responseBody= item1.getJSONObject(i);
                        //responseBody=responseBody.getJSONObject("youtube");
                        if(responseBody.getString("key")!=null) {
                            youtube = "http://www.youtube.com/watch?v=" + responseBody.getString("key");
                            id.setText(Html.fromHtml("<b>Video:</b>" + responseBody.getString("name")));
                            thumb = "http://img.youtube.com/vi/" + responseBody.getString("key") + "/0.jpg";
                            loadThumbnail(thumb);
                        }



                        Log.d("youtube", "http://www.youtube.com/watch?v="+responseBody.getString("key"));
                        Log.d("thumbnail",thumb);
                    }
                } catch (JSONException e) {
                    Log.v(LOG_TAG, "Movies with id ", e);
                    e.printStackTrace();
                }
            }
        });



    }


    public  void loadThumbnail(String thumb){
        Picasso.with(this).load(thumb).error(R.drawable.small_movie_poster).into(thumbnail);
        thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(youtube)));
            }
        });

    }



}


