package com.livos.companionplants.ui.plants;


import android.os.Bundle;

import com.livos.companionplants.data.DataManager;
import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.ui.base.BasePresenter;
import com.livos.companionplants.ui.events.PlantSelectedEvent;
import com.livos.companionplants.ui.main.MainActivity;

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

        // We only fill the tabs recyclerview if a plant has been selected or we are on the default sceen with all
        // plants and no tabs (tabindex = -1)
        if(selectedPlant != null || (selectedPlant == null && tabIndex == -1)) {
            List<AssociatedPlant> plants = getAssociatedplants(selectedPlant, tabIndex);

            getMvpView().loadPlants(plants);
        }


    }

    /**
     * Get plants associated with the one selected
     * @param selectedPlant
     * @param tabIndex current tab, needed to know which associations type to get
     * @return associated plants
     */
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

    /**
     * A plant has been selected by user
     * @param plantSelectedEvent Event contanining the selected plant
     * @param tabIndex
     */
    public void onSelectedPlantChanged(PlantSelectedEvent plantSelectedEvent, int tabIndex) {
        getDataManager().setSelectedPlant(plantSelectedEvent.getPlant());
        this.tabIndex = tabIndex;
        onViewPrepared(tabIndex);
    }
}
