package com.livos.companionplants.ui.plants;


import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.ui.base.MvpPresenter;
import com.livos.companionplants.ui.events.PlantSelectedEvent;

public interface PlantsMvpPresenter<V extends PlantsMvpView>
    extends MvpPresenter<V> {

    void onViewPrepared();
    void onSelectedPlantChanged(PlantSelectedEvent plantSelectedEvent);

}
