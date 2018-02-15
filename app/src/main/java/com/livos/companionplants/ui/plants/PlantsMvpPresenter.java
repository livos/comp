package com.livos.companionplants.ui.plants;


import com.livos.companionplants.ui.base.MvpPresenter;
import com.livos.companionplants.ui.events.PlantSelectedEvent;

public interface PlantsMvpPresenter<V extends PlantsMvpView>
    extends MvpPresenter<V> {

    void onViewPrepared(int tabIndex);
    void onSelectedPlantChanged(PlantSelectedEvent plantSelectedEvent, int tabIndex);

}
