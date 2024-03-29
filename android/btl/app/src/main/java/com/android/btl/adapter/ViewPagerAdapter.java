package com.android.btl.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.android.btl.fragment.Fragment1;
import com.android.btl.fragment.Fragment2;
import com.android.btl.fragment.Fragment3;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return  new Fragment1();
            case 1: return  new Fragment2();
            case 2: return  new Fragment3();
            default: return  new Fragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
