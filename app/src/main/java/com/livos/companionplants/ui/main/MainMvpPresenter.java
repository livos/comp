package com.livos.companionplants.ui.main;


import com.livos.companionplants.di.scopes.PerActivity;
import com.livos.companionplants.ui.base.MvpPresenter;
import com.livos.companionplants.ui.base.MvpView;
import com.livos.companionplants.ui.events.PlantSelectedEvent;

@PerActivity
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {
    void onSelectedPlantChanged(PlantSelectedEvent plantSelectedEvent);


}
