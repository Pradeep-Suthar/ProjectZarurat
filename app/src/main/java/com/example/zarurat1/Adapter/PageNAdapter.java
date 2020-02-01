package com.example.zarurat1.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.zarurat1.Fragment.PersonlizedNewsFragment;
import com.example.zarurat1.Fragment.RecentNewsFragment;

public class PageNAdapter extends FragmentPagerAdapter {
    int tabCount;

    public PageNAdapter(FragmentManager fm,int tabCount) {
        super(fm);
        this.tabCount=tabCount;
    }

    @Override
    public Fragment getItem(int i) {
        switch(i){
            case 0:
                RecentNewsFragment tab1=new RecentNewsFragment();
                return tab1;
            case 1:
                PersonlizedNewsFragment tab2=new PersonlizedNewsFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
