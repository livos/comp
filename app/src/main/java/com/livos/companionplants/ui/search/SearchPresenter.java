package com.livos.companionplants.ui.search;


import android.os.Bundle;
import android.util.Log;

import com.livos.companionplants.data.DataManager;
import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.data.local.db.model.PlantDefinition;
import com.livos.companionplants.ui.base.BasePresenter;
import com.livos.companionplants.ui.events.PlantSelectedEvent;

import java.util.List;

import javax.inject.Inject;

public class SearchPresenter <V extends SearchMvpView> extends BasePresenter<V>
        implements SearchMvpPresenter<V>  {

    private Plant selectedPlant = null;

    @Inject
    public SearchPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onViewPrepared() {
        List<PlantDefinition> plantsDefinitions = getDataManager().getAllDefinitions();
        getMvpView().loadDefinitions(plantsDefinitions);
    }

    public void onListPlantClicked(PlantDefinition plantDefinition) {
        selectedPlant = getDataManager().getPlantById(plantDefinition.getId());
        getDataManager().setSelectedPlant(selectedPlant);
        getMvpView().onSelectedPlantChanged(selectedPlant);

    }

    public void onSelectedPlantChanged(PlantSelectedEvent plantSelectedEvent) {

        getMvpView().setCurrentPlant(plantSelectedEvent.getPlant());
        this.selectedPlant = plantSelectedEvent.getPlant();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if(outState != null && selectedPlant != null) {
            outState.putLong("plantSelectedId", selectedPlant.getId());
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Long savedStatePlantId = savedInstanceState.getLong("plantSelectedId");
        selectedPlant = getDataManager().getPlantById(savedStatePlantId);
        getDataManager().setSelectedPlant(selectedPlant);
        getMvpView().setCurrentPlant(selectedPlant);
    }

}
