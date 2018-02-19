package com.livos.companionplants.data;


import com.livos.companionplants.data.local.db.DbHelper;
import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.data.local.db.model.PlantDefinition;
import com.livos.companionplants.data.local.prefs.PreferencesHelper;
import com.livos.companionplants.data.local.state.StateHelper;
import com.livos.companionplants.ui.events.PlantSelectedEvent;
import com.livos.companionplants.ui.events.PlantSelectedEventImpl;
import com.livos.companionplants.ui.plants.AssociatedPlant;

import java.util.List;

import javax.inject.Inject;

public class AppDataManager implements DataManager {
    private final DbHelper dbHelper;
    private final StateHelper stateHelper;

    @Inject
    public AppDataManager(DbHelper dbHelper, PreferencesHelper preferencesHelper, StateHelper stateHelper) {
        this.dbHelper = dbHelper;
        this.stateHelper = stateHelper;
    }

    @Override
    public List<AssociatedPlant> getAllPlants() {
        return dbHelper.getAllPlants();
    }

    @Override
    public List<PlantDefinition> getAllDefinitions() {
        return dbHelper.getAllDefinitions();
    }

    @Override
    public List<AssociatedPlant> getAssociatedPlants(long plantId) {
        return  dbHelper.getAssociatedPlants(plantId);
    }

    @Override
    public List<AssociatedPlant> getAssociatedPlantsByFlag(long plantId, long flagId) {
        return dbHelper.getAssociatedPlantsByFlag(plantId, flagId);
    }

    @Override
    public List<AssociatedPlant> getAssociatedPlantsHelps(long plantId) {
        return dbHelper.getAssociatedPlantsHelps(plantId);
    }

    @Override
    public List<AssociatedPlant> getAssociatedPlantsHelpedBy(long plantId) {
        return dbHelper.getAssociatedPlantsHelpedBy(plantId);
    }

    @Override
    public List<AssociatedPlant> getAssociatedPlantsAvoid(long plantId) {
        return dbHelper.getAssociatedPlantsAvoid(plantId);
    }

    @Override
    public List<AssociatedPlant> getAssociatedPlantsNeutral(long plantId) {
        return dbHelper.getAssociatedPlantsNeutral(plantId);
    }


    @Override
    public Plant getPlantById(long plantId) {
        return  dbHelper.getPlantById(plantId);
    }

    @Override
    public Plant getPlantByName(String name) {
        return dbHelper.getPlantByName(name);
    }


    @Override
    public void setSelectedPlant(Plant selectedPlant) {
        stateHelper.setSelectedPlant(selectedPlant);
    }

    @Override
    public Plant getSelectedPlant() {
        return stateHelper.getSelectedPlant();
    }
}
