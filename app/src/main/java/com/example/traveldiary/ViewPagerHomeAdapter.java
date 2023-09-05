package com.example.traveldiary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerHomeAdapter extends FragmentPagerAdapter {
    public ViewPagerHomeAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position==0){
            return new PopularFragment();
        } else if (position==1) {
            return new FollowingFragment();
        }else{
            return new MostViewedFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position==0){
            return "Popular";
        } else if (position==1) {
            return "Following";

        }else {
            return "Most Viewed";

        }
    }
}
