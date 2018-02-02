package com.livos.companionplants.ui.plants;


import com.livos.companionplants.data.DataManager;
import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.ui.base.BasePresenter;
import com.livos.companionplants.ui.events.PlantSelectedEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class PlantsPresenter<V extends PlantsMvpView> extends BasePresenter<V>
    implements PlantsMvpPresenter<V> {

    @Inject
    public PlantsPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void onViewPrepared() {
        List<Plant> plants = getDataManager().getAllPlants();
        getMvpView().loadPlants(plants);
    }

//    @Override
//    public void onCardPlantClicked(Plant plant) {
//        getDataManager().setSelectedPlant(plant);
//        onSelectedPlantChanged();
//    }

    public void onSelectedPlantChanged(PlantSelectedEvent plantSelectedEvent) {
        Plant selectedPlant = plantSelectedEvent.getPlant();

        // If selected plant is null it means that plant has been selected by clicking
        // on a list item ant not on picture
        if(selectedPlant == null) {
            selectedPlant = getDataManager().getSelectedPlant();
        }

        getDataManager().setSelectedPlant(selectedPlant);

        List<Plant> associatedPlants = getDataManager().getAssociatedPlants(selectedPlant.getId());
        getMvpView().loadPlants(associatedPlants);

    }


}
