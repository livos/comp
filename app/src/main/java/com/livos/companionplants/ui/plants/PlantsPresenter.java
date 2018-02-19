package com.livos.companionplants.ui.plants;


import android.os.Bundle;

import com.livos.companionplants.data.DataManager;
import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.ui.base.BasePresenter;
import com.livos.companionplants.ui.events.PlantSelectedEvent;

import java.util.List;

import javax.inject.Inject;

public class PlantsPresenter<V extends PlantsMvpView> extends BasePresenter<V>
    implements PlantsMvpPresenter<V> {
    Plant selectedPlant = null;
    int tabIndex = -1;


    @Inject
    public PlantsPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void onViewPrepared(int tabIndex) {
        this.tabIndex = tabIndex;
        selectedPlant = getDataManager().getSelectedPlant();
        List<AssociatedPlant> plants = getAssociatedplants(selectedPlant,tabIndex);
        getMvpView().loadPlants(plants);
    }

    private List<AssociatedPlant> getAssociatedplants(Plant selectedPlant, int tabIndex) {
        //List<AssociatedPlant> associatedPlants = getDataManager().getAssociatedPlants(selectedPlant.getId());
        List<AssociatedPlant> associatedPlants = null;
        switch (tabIndex) {
            case -1:
                associatedPlants =  getDataManager().getAllPlants();
                break;
            case 0: // helps
                associatedPlants = getDataManager().getAssociatedPlantsByFlag(selectedPlant.getId(),1);
                break;
            case 1: // helped by
                associatedPlants = getDataManager().getAssociatedPlantsByFlag(selectedPlant.getId(),2);
                break;
            case 2: // avoid
                associatedPlants = getDataManager().getAssociatedPlantsByFlag(selectedPlant.getId(),5);
                break;
            case 3: // neutral
                associatedPlants = getDataManager().getAssociatedPlantsByFlag(selectedPlant.getId(),6);
                break;
        }
        return associatedPlants;
    }

    public void onSelectedPlantChanged(PlantSelectedEvent plantSelectedEvent, int tabIndex) {
        getDataManager().setSelectedPlant(plantSelectedEvent.getPlant());
        this.tabIndex = tabIndex;
        onViewPrepared(tabIndex);
    }
}
