package com.livos.companionplants.ui.main;


import android.app.Fragment;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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
    private static final int NO_TABS = -1;
    private int selectedTab = NO_TABS;

    private ActionBarDrawerToggle drawerToggle;

    @Inject
    MainMvpPresenter<MainMvpView> presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.dl_main)
    DrawerLayout dlMain;

    @BindView(R.id.vp_plants)
    ViewPager vpPlants;

    @BindView(R.id.tl_plants)
    TabLayout tlPlants;

    @BindView(R.id.nv_main)
    NavigationView nvMain;

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
        setSupportActionBar(toolbar);
        drawerToggle = new ActionBarDrawerToggle(
                this,
                dlMain,
                toolbar,
                R.string.open_drawer,
                R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideKeyboard();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        dlMain.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        setupNavMenu();
        presenter.onNavMenuCreated();


        PlantsPagerAdapter plantsPagerAdapter = new PlantsPagerAdapter(getSupportFragmentManager(), this);
        vpPlants.setAdapter(plantsPagerAdapter);

        tlPlants.setupWithViewPager(vpPlants);

    }

    private void setupNavMenu() {
        //View headerLayout = nvMain.getHeaderView(0);

        nvMain.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        dlMain.closeDrawer(GravityCompat.START);
                        switch (item.getItemId()) {
//                            case R.id.nav_item_help:
//                                //presenter.onDrawerOptionAboutClick();
//                                return true;
                            case R.id.nav_item_rate_us:
                                //presenter.onDrawerRateUsClick();
                               // rateApp(MainActivity.this);
                                return true;
                            case R.id.nav_item_credits:
                                //showCredits();
                                return true;
//                            case R.id.nav_item_feedback:
//                                return true;
                            case R.id.nav_item_share:
                                //shareApp(MainActivity.this);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
    }

    private void showPlantsFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                //.disallowAddToBackStack()
                .add(R.id.fl_container_plants, PlantsFragment.newInstance(NO_TABS), PlantsFragment.TAG) // -1 : showing
                .commit();

    }

    private void showSearchFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                //.disallowAddToBackStack()
                .add(R.id.fl_container_search, SearchFragment.newInstance(), SearchFragment.TAG)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("selectedTab",selectedTab);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        selectedTab = savedInstanceState.getInt("selectedTab");

        if(selectedTab == -1) {
            toggleTabs(View.GONE);
        } else {
            toggleTabs(View.VISIBLE);
        }
    }

    @Subscribe
    public void onSelectedPlantEvent(PlantSelectedEvent plantSelectedEvent) {
        toggleTabs(View.VISIBLE);

        // a plants has been selected, selected tab is set to a value different of NO_TABS to be
        // able to see a plant has been selected and we have to set the tabs visible
        selectedTab = 0;
    }

    /**
     * Toggle tabs visibility
     * @param visibility
     */
    private void toggleTabs(int visibility) {
        tlPlants.setVisibility(visibility); // Show TableLayout
        vpPlants.setVisibility(visibility); // Show ViewPager
        if (visibility == View.VISIBLE) {
            // Hide FrameLayout with all plants (not tab and no filter applied => welcome screen)
            flContainerPlants.setVisibility(View.GONE);
        } else {
            flContainerPlants.setVisibility(View.VISIBLE);
        }
    }

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
