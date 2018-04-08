package com.livos.companionplants.ui.main;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.livos.companionplants.data.DataManager;
import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.ui.base.BasePresenter;
import com.livos.companionplants.ui.base.MvpPresenter;
import com.livos.companionplants.ui.base.MvpView;
import com.livos.companionplants.ui.events.PlantSelectedEvent;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;


public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {

    @Inject
    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);

        // App has just been started, no plant has been already selected
        getDataManager().setSelectedPlant(null);
    }

    // Get data to use in navbar (app version ...etc)
    @Override
    public void onNavMenuCreated() {

    }



    @Override
    public void setTabsVisibility(long plantId) {

        int[] tabs  = {
                getDataManager().getAssociatedPlantsHelps(plantId).size(),
                getDataManager().getAssociatedPlantsHelpedBy(plantId).size(),
                getDataManager().getAssociatedPlantsAvoid(plantId).size(),
                getDataManager().getAssociatedPlantsNeutral(plantId).size()
        };

        int tabIdx = 0;
        for(int tabNbElement : tabs) {
            getMvpView().toggleTab(tabIdx,tabNbElement != 0);

            tabIdx++;
        }

    }

    @Override
    public void onDrawerCreditsClick() {
        getMvpView().showCredits();
    }

    @Override
    public void onDrawerShareAppClick() {
        getMvpView().shareApp();
    }

    @Override
    public void onDrawerRateUsClick() {

        getMvpView().closeNavigationDrawer();
        getMvpView().rateApp();
    }


}
