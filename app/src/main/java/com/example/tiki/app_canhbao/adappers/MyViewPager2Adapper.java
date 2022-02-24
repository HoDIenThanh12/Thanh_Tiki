package com.example.tiki.app_canhbao.adappers;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.tiki.app_canhbao.fragments.FragmentAbout;
import com.example.tiki.app_canhbao.fragments.FragmentHome;
import com.example.tiki.app_canhbao.fragments.FragmentMeetting;
import com.example.tiki.app_canhbao.fragments.FragmentTimeTable;

public class MyViewPager2Adapper extends FragmentStateAdapter {
    public MyViewPager2Adapper(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new FragmentHome();
            case 1:
                return new FragmentMeetting();
            case 2:
                return new FragmentTimeTable();
            case 3:
                return new FragmentAbout();
            default:
                return new FragmentHome();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
