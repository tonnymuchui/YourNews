package com.yournews.yournews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class Pager extends FragmentStatePagerAdapter {

    int tabCount;

    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                Trending trending = new Trending();
                return trending;
            case 1:
                Business business = new Business();
                return business;
            case 2:
           Politicts politicts = new Politicts();
           return politicts;
           default: return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
