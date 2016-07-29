package com.terminali.moviedb.MovieGenreFragments;

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

public class GenreMovieFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public GenreMovieFragment() {
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
        GenreMovieFragment.ViewPagerAdapter adapter = new GenreMovieFragment.ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new Action(),"Action");
        adapter.addFragment(new Adventure(),"Adventure");
        adapter.addFragment(new Animation(),"Animation");
        adapter.addFragment(new Comedy(),"Comedy");
        adapter.addFragment(new Crime(),"Crime");
        adapter.addFragment(new Documentry(),"Documentary");
        adapter.addFragment(new Drama(),"Drama");
        adapter.addFragment(new Family(),"Family");
        adapter.addFragment(new Fantasy(),"Fantasy");
        adapter.addFragment(new History(),"History");
        adapter.addFragment(new Horror(),"Horror");
        adapter.addFragment(new Music(),"Music");
        adapter.addFragment(new Mystery(),"Mystery");
        adapter.addFragment(new Romance(),"Romance");
        adapter.addFragment(new ScienceFiction(),"Science Fiction");
        adapter.addFragment(new Thriller(),"Thriller");
        adapter.addFragment(new TVMovie(),"TV movie");
        adapter.addFragment(new War(),"War");
        adapter.addFragment(new Western(),"Western");



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