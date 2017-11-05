package com.livos.companionplants.main;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.google.firebase.crash.FirebaseCrash;
import com.livos.companionplants.R;
import com.livos.companionplants.application.PlantsApplication;
import com.livos.companionplants.plants.PlantsFragment;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @Inject
    MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FirebaseCrash.report(new Exception("My first Android non-fatal error"));


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

//        Fragment fragmentSearch = fm.findFragmentById(R.id.fl_container_search);
//        if (fragmentSearch == null) {
//            fragmentSearch = new SearchFragment();
//            fm.beginTransaction()
//                    .add(R.id.fl_container_search, fragmentSearch)
//                    .commit();
//        }
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




}
