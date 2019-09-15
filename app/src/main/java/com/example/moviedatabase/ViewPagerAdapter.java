package com.example.moviedatabase;

import android.util.Log;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<Movie> popularList;
    List<Movie> topRatedList;

    public ViewPagerAdapter(@NonNull FragmentManager fm, List<Movie> popularList, List<Movie> topRatedList) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.popularList = popularList;
        this.topRatedList = topRatedList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        ListFragment listFragment ;

        switch (position){
            case 0:
                listFragment = new ListFragment(popularList);
                break;
            case 1:
                listFragment = new ListFragment(topRatedList);
                break;
            case 2:
                listFragment = new ListFragment(popularList);
                break;
            default:
                listFragment = new ListFragment(popularList);
                break;
        }

        return listFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";

        switch (position) {
            case 0:
                title = "Popular";
                break;
            case 1:
                title = "Top Rated";
                break;
            case 2:
                title = "Favourite";
                break;
            default:
                title = "Movies";
        }
        return title;
    }
}
