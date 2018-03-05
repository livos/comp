package com.livos.companionplants.di.module;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.livos.companionplants.di.qualifier.ActivityContext;
import com.livos.companionplants.di.scopes.PerActivity;
import com.livos.companionplants.ui.main.MainMvpPresenter;
import com.livos.companionplants.ui.main.MainMvpView;
import com.livos.companionplants.ui.main.MainPresenter;
import com.livos.companionplants.ui.main.adapters.PlantsPagerAdapter;
import com.livos.companionplants.ui.plants.PlantsMvpPresenter;
import com.livos.companionplants.ui.plants.PlantsMvpView;
import com.livos.companionplants.ui.plants.PlantsPresenter;
import com.livos.companionplants.ui.plants.adapters.PlantsRecyclerViewAdapter;
import com.livos.companionplants.ui.search.SearchMvpPresenter;
import com.livos.companionplants.ui.search.SearchMvpView;
import com.livos.companionplants.ui.search.SearchPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return activity;
    }

    @Provides
    PlantsMvpPresenter<PlantsMvpView> providePlantsMvpPresenter(PlantsPresenter<PlantsMvpView> presenter) {
        return presenter;
    }

    @Provides
    SearchMvpPresenter<SearchMvpView> provideSearchMvpPresenter(SearchPresenter<SearchMvpView> presenter) {
        return  presenter;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

}
