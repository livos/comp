package com.livos.companionplants.ui.main.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.livos.companionplants.R;
import com.livos.companionplants.ui.plants.PlantsFragment;

/**
 * Created by laurent on 2/7/18.
 */

public class PlantsPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    public PlantsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int tabIndex) {

        return PlantsFragment.newInstance(tabIndex);
    }

    @Override
    public int getCount() {
        return 4;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int tabIndex) {
        // Generate title based on item position
        switch (tabIndex) {
            case 0:
                return context.getResources().getString(R.string.title_helps);
            case 1:
                return context.getResources().getString(R.string.title_helped_by);
            case 2:
                return context.getResources().getString(R.string.title_avoid);
            case 3:
                return context.getResources().getString(R.string.title_neutral);
            default:
                return null;
        }
    }
}
