package com.terminali.moviedb.TVGenreFragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.terminali.moviedb.R;

import java.util.ArrayList;
import java.util.List;

public class TvGenreFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public TvGenreFragment() {
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


        View view = inflater.inflate(R.layout.genre_main_fragment, container, false);
        viewPager=(ViewPager)view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }



    private void setupViewPager(ViewPager viewPager) {
        TvGenreFragment.ViewPagerAdapter adapter = new TvGenreFragment.ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new ActionNAdventure(),"Action & Adventure");
        adapter.addFragment(new AnimationTv(),"Animation");
        adapter.addFragment(new ComedyTv(),"Comedy");
        adapter.addFragment(new CrimeTv(),"Crime");
        adapter.addFragment(new DocumentryTv(),"Documentary");
        adapter.addFragment(new DramaTv(),"Drama");
        adapter.addFragment(new FamilyTv(),"Family");
        adapter.addFragment(new KidsTV(),"Kids");
        adapter.addFragment(new MysteryTv(),"Mystery");
        adapter.addFragment(new News(),"News");
        adapter.addFragment(new Reality(),"Reality");
        adapter.addFragment(new ScienceFictionTv(),"Sci-Fi & Fantasy");
        adapter.addFragment(new Soap(),"Soap");
        adapter.addFragment(new Talk(),"Talk");

        adapter.addFragment(new WarNPolitics(),"War & Politics");
        adapter.addFragment(new WesternTv(),"Western");



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
            return mFragmentTitleList.get(position);
        }
    }

}