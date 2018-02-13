package com.livos.companionplants.ui.plants;


import com.livos.companionplants.data.DataManager;
import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.ui.base.BasePresenter;
import com.livos.companionplants.ui.events.PlantSelectedEvent;

import java.util.List;

import javax.inject.Inject;

public class PlantsPresenter<V extends PlantsMvpView> extends BasePresenter<V>
    implements PlantsMvpPresenter<V> {
    Plant selectedPlant = null;


    @Inject
    public PlantsPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void onViewPrepared(int tabNumber) {
        selectedPlant = getDataManager().getSelectedPlant();
        List<AssociatedPlant> plants = getAssociatedplants(selectedPlant,tabNumber);
        if(tabNumber == -1) {
            getMvpView().loadPlants(plants, true);
        } else {
            getMvpView().loadPlants(plants);
        }
    }

    private List<AssociatedPlant> getAssociatedplants(Plant selectedPlant, int tabNumber) {
        //List<AssociatedPlant> associatedPlants = getDataManager().getAssociatedPlants(selectedPlant.getId());
        List<AssociatedPlant> associatedPlants = null;
        switch (tabNumber) {
            case -1:
                associatedPlants =  getDataManager().getAllPlants();
                break;
            case 0:
                associatedPlants = getDataManager().getAssociatedPlantsHelps(selectedPlant.getId());
                break;
            case 1:
                associatedPlants = getDataManager().getAssociatedPlantsHelpedBy(selectedPlant.getId());
                break;
            case 2:
                associatedPlants = getDataManager().getAssociatedPlantsAvoid(selectedPlant.getId());
                break;
            case 3:
                associatedPlants = getDataManager().getAssociatedPlantsNeutral(selectedPlant.getId());
                break;
        }
        return associatedPlants;
    }

//    @Override
//    public void onCardPlantClicked(Plant plant) {
//        getDataManager().setSelectedPlant(plant);
//        onSelectedPlantChanged();
//    }

    public void onSelectedPlantChanged(PlantSelectedEvent plantSelectedEvent) {
        getDataManager().setSelectedPlant(plantSelectedEvent.getPlant());

//        if(plantSelectedEvent != null) {
//            selectedPlant = plantSelectedEvent.getPlant();
//        }
//
//        // If selected plant is null it means that plant has been selected by clicking
//        // on a list item ant not on picture
//        if(selectedPlant == null) {
//            selectedPlant = getDataManager().getSelectedPlant();
//        }



//        List<AssociatedPlant> associatedPlants = getAssociatedplants(selectedPlant,-1);
//
//
//        getMvpView().loadPlants(associatedPlants);

    }


}
