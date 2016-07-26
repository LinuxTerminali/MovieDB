package com.terminali.moviedb;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class TvShowFragment extends Fragment {
    private final String LOG_TAG = MovieDBClient.class.getSimpleName();
    private TabLayout tabLayout;
    private ViewPager viewPager;


    //private OnFragmentInteractionListener mListener;

    public TvShowFragment() {
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


        View view = inflater.inflate(R.layout.top_home_fragment, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }


    private void setupViewPager(ViewPager viewPager) {
        TvShowFragment.ViewPagerAdapter adapter = new TvShowFragment.ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new PopularTvShow(), "Popular TV Shows");
        adapter.addFragment(new OnAirTvShow(), "On Air");
        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            mFragmentTitleList.add("Latest Release");
            mFragmentTitleList.add("Top Rated");
            return mFragmentTitleList.get(position);
        }
    }
}

