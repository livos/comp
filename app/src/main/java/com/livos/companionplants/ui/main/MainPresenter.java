package com.livos.companionplants.ui.main;


import android.os.Bundle;

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


}
