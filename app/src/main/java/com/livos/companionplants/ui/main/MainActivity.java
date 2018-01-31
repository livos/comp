package com.livos.companionplants.ui.main;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import com.livos.companionplants.R;
import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.ui.base.BaseActivity;
import com.livos.companionplants.ui.plants.PlantsFragment;
import com.livos.companionplants.ui.search.SearchFragment;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainMvpView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        // Avoid the multiple creations of fragments when changing the device orientation
        if(savedInstanceState == null) {
            showSearchFragment();
            showPlantsFragment();
        }
    }

//    @Override
//    public void onFragmentDetached(String tag) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        android.support.v4.app.Fragment fragment = fragmentManager.findFragmentByTag(tag);
//        if (fragment != null) {
//            fragmentManager
//                    .beginTransaction()
//                    .disallowAddToBackStack()
//                    .remove(fragment)
//                    .commitNow();
//        }
//    }

    private void showPlantsFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .add(R.id.fl_container_plants, PlantsFragment.newInstance(), PlantsFragment.TAG)
                .commit();

    }

    private void showSearchFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .add(R.id.fl_container_search, SearchFragment.newInstance(), SearchFragment.TAG)
                .commit();
    }
}
