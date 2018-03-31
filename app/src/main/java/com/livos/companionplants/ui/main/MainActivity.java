package com.livos.companionplants.ui.main;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TableLayout;

import com.livos.companionplants.R;
import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.di.component.ActivityComponent;
import com.livos.companionplants.ui.base.BaseActivity;
import com.livos.companionplants.ui.events.EmptyTabEvent;
import com.livos.companionplants.ui.events.PlantSelectedEvent;
import com.livos.companionplants.ui.main.adapters.PlantsPagerAdapter;
import com.livos.companionplants.ui.plants.PlantsFragment;
import com.livos.companionplants.ui.search.SearchFragment;
import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.livos.companionplants.utils.AppUtils.rateApplication;


public class MainActivity extends BaseActivity implements MainMvpView {
    private static final int NO_TABS = -1;
    private int selectedTab = NO_TABS;
    private String localeCode;

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


        //== Drawer ==
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

        // == Viewpager ==
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
                           // case R.id.nav_item_help:
//                                //presenter.onDrawerOptionAboutClick();
//                                return true;
                            case R.id.nav_item_rate_us:
                                presenter.onDrawerRateUsClick();
                               // rateApp(MainActivity.this);
                                return true;
                            case R.id.nav_item_credits:
                                presenter.onDrawerCreditsClick();
                                return true;
//                            case R.id.nav_item_feedback:
//                                return true;
                            case R.id.nav_item_share:
                                presenter.onDrawerShareAppClick();
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
                // .disallowAddToBackStack()
                .add(R.id.fl_container_plants, PlantsFragment.newInstance(NO_TABS), PlantsFragment.TAG) // -1 : showing
                .commit();

    }

    @Override
    public void rateApp() {
        rateApplication(MainActivity.this);
    }

    @Override
    public void shareApp(){
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, MainActivity.this.getString(R.string.app_name));
            String message = getString(R.string.share_app_message);
            String url =  getString(R.string.app_details_base_url);
            String sAux = String.format("%s%s?id=%s",message, url,  MainActivity.this.getPackageName());
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            MainActivity.this.startActivity(Intent.createChooser(i, "choose one"));
    }

    @Override
    public void closeNavigationDrawer() {

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
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Drawable drawable = item.getIcon();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }

        switch (item.getItemId()) {
            case R.id.mi_reload:
                toggleTabs(View.GONE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    private void selectFirstVisibleTab() {
        boolean visibleTabFound = false;
        int tabIdx = 0;
        while(!visibleTabFound) {
            View v = ((ViewGroup) tlPlants.getChildAt(0)).getChildAt(tabIdx);
            if(v.getVisibility() == View.VISIBLE) {
                TabLayout.Tab tab = tlPlants.getTabAt(tabIdx);
                tab.select();
                visibleTabFound = true;
            }
            tabIdx++;
        }
    }

    @Subscribe
    public void onSelectedPlantEvent(PlantSelectedEvent plantSelectedEvent) {
        toggleTabs(View.VISIBLE);

        presenter.setTabsVisibility(plantSelectedEvent.getPlant().getId());

        selectFirstVisibleTab();

        selectedTab = 0;
    }

    /**
     * Toggle all tabs visibility
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

    /**
     * Toggle single tab visibility
     * @param taIdx
     * @param enabled
     */
    @Override
    public void toggleTab(int taIdx, boolean enabled) {
        ((ViewGroup) tlPlants.getChildAt(0)).getChildAt(taIdx).setEnabled(enabled);//setVisibility(visibility);
        if(!enabled) {
            ((ViewGroup) tlPlants.getChildAt(0)).getChildAt(taIdx).setBackgroundColor(getResources().getColor(R.color.grey_green));
        } else {
            ((ViewGroup) tlPlants.getChildAt(0)).getChildAt(taIdx).setBackgroundColor(getResources().getColor(R.color.green));
        }
    }

    /**
     * Show application credits
     */
    @Override
    public void showCredits() {
        new LibsBuilder()
                //provide a style (optional) (LIGHT, DARK, LIGHT_DARK_TOOLBAR)
                .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
                //start the activity
                .start(MainActivity.this);

    }


}
