package com.terminali.moviedb;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * Created by TERMINALi on 7/22/2016.
 */

public class SlideShowAdapter extends PagerAdapter {
    private String basePosterPath="http://image.tmdb.org/t/p/w185/";
    Context mcontext;
    LayoutInflater mLayoutInflater;
    public SlideShowAdapter(Context context){
        mcontext=context;
        mLayoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
   /* public void listMethod() {
      fetch();
    }*/

    @Override
    public int getCount(){
        return 20;
    }
    @Override
    public boolean isViewFromObject(View view, Object object){
        return true;

    }
   @Override
    public View instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item,container,false);
       ImageView imageView=(ImageView)itemView.findViewById(R.id.imageView);
       imageView.setImageResource(R.drawable.small_movie_poster);
       container.addView(itemView);
       //listMethod();
        return itemView;
    }

}
