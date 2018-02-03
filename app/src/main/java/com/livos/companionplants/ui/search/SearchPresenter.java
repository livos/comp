package com.livos.companionplants.ui.search;


import com.livos.companionplants.data.DataManager;
import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.data.local.db.model.PlantDefinition;
import com.livos.companionplants.ui.base.BasePresenter;
import com.livos.companionplants.ui.events.PlantSelectedEvent;

import java.util.List;

import javax.inject.Inject;

public class SearchPresenter <V extends SearchMvpView> extends BasePresenter<V>
        implements SearchMvpPresenter<V>  {

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
        Plant selectedPlant = getDataManager().getPlantById(plantDefinition.getId());
        getDataManager().setSelectedPlant(selectedPlant);
        getMvpView().onSelectedPlantChanged();

    }

    public void onSelectedPlantChanged(PlantSelectedEvent plantSelectedEvent) {
        Plant selectedPlant = plantSelectedEvent.getPlant();

        if(selectedPlant == null) {
            selectedPlant = getDataManager().getSelectedPlant();
        }

        getMvpView().setCurrentPlant(selectedPlant);
    }

}
