package com.livos.companionplants.ui.main;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TableLayout;

import com.livos.companionplants.R;
import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.di.component.ActivityComponent;
import com.livos.companionplants.ui.base.BaseActivity;
import com.livos.companionplants.ui.events.PlantSelectedEvent;
import com.livos.companionplants.ui.main.adapters.PlantsPagerAdapter;
import com.livos.companionplants.ui.plants.PlantsFragment;
import com.livos.companionplants.ui.search.SearchFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView> presenter;

    @BindView(R.id.vp_plants)
    ViewPager vpPlants;

    @BindView(R.id.tl_plants)
    TabLayout tlPlants;

    @BindView(R.id.fl_container_plants)
    FrameLayout flContainerPlants;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        presenter.onAttach(this);

        setUp();




        // Avoid the multiple creations of fragments when changing the device orientation
        if(savedInstanceState == null) {
            showSearchFragment();
            showPlantsFragment();
        }
    }

    @Override
    protected void setUp() {
        PlantsPagerAdapter plantsPagerAdapter = new PlantsPagerAdapter(getSupportFragmentManager(), this);
        vpPlants.setAdapter(plantsPagerAdapter);

        tlPlants.setupWithViewPager(vpPlants);
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

    @Subscribe
    public void onEvent(PlantSelectedEvent plantSelectedEvent) {

        presenter.onSelectedPlantChanged(plantSelectedEvent);
        tlPlants.setVisibility(View.VISIBLE);
        vpPlants.setVisibility(View.VISIBLE);
        flContainerPlants.setVisibility(View.GONE);
    }


//    @Override
//    public void hideAllPlantsView() {
//        flContainerPlants.setVisibility(View.GONE);
//    }

    @Override
    public void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
