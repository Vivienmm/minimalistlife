package com.su.vivienmm.minimalist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

/**
 * Created by chinaso on 2017/11/28.
 */

public class HomePagerAdapter extends FragmentStatePagerAdapter {

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new MemoFragment();

            case 1:
                return new ScheduleFragment();
            case 2:
                return new EssayFragment();

        }
        return new ScheduleFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

}
