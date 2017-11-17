package com.livos.companionplants.main;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import com.livos.companionplants.R;
import com.livos.companionplants.application.PlantsApplication;
import com.livos.companionplants.plants.PlantsFragment;
import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;

import static com.livos.companionplants.util.KeyboardUtil.hideSoftInput;
import static com.livos.companionplants.util.AppUtils.rateApp;
import static com.livos.companionplants.util.AppUtils.shareApp;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @Inject
    MainContract.Presenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.dl_main)
    DrawerLayout dlMain;

    @BindView(R.id.nv_main)
    NavigationView nvMain;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    public void onResume() {
        super.onResume();
        if (dlMain != null)
            dlMain.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    public void lockDrawer() {
        if (dlMain != null)
            dlMain.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void unlockDrawer() {
        if (dlMain != null)
            dlMain.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        // Not showing the keyboard when app start or configuration change
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        ((PlantsApplication) getApplication()).getMainComponent().inject(this);

        FragmentManager fm = getSupportFragmentManager();

        Fragment fragmentPlants = fm.findFragmentById(R.id.fl_container_plants);
        if (fragmentPlants == null) {
            fragmentPlants = new PlantsFragment();
            fm.beginTransaction()
                    .add(R.id.fl_container_plants, fragmentPlants)
                    .commit();
        }

        setUp();


    }

    @Override
    public void setUp() {
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
                hideSoftInput(MainActivity.this);
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
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.setView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }

    // Show external libraries credits
    private void showCredits() {
        new LibsBuilder()
                //provide a style (optional) (LIGHT, DARK, LIGHT_DARK_TOOLBAR)
                .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
                //start the activity
                .start(MainActivity.this);
    }

    void setupNavMenu() {
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
                                rateApp(MainActivity.this);
                                return true;
                            case R.id.nav_item_credits:
                                showCredits();
                                return true;
//                            case R.id.nav_item_feedback:
//                                return true;
                            case R.id.nav_item_share:
                                shareApp(MainActivity.this);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
    }






}
