package com.livos.companionplants.data;


import com.livos.companionplants.data.local.db.DbHelper;
import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.data.local.db.model.PlantDefinition;
import com.livos.companionplants.data.local.prefs.PreferencesHelper;
import com.livos.companionplants.data.local.state.StateHelper;
import com.livos.companionplants.ui.events.PlantSelectedEvent;
import com.livos.companionplants.ui.events.PlantSelectedEventImpl;

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
    public List<Plant> getAllPlants() {
        return dbHelper.getAllPlants();
    }

    @Override
    public List<PlantDefinition> getAllDefinitions() {
        return dbHelper.getAllDefinitions();
    }

    @Override
    public List<Plant> getAssociatedPlants(long plantId) {
        return  dbHelper.getAssociatedPlants(plantId);
    }

    @Override
    public Plant getPlantById(long plantId) {
        return  dbHelper.getPlantById(plantId);
    }


    @Override
    public void setSelectedPlant(Plant selectedPlant) {
        stateHelper.setSelectedPlant(selectedPlant);
        PlantSelectedEvent listItemEvent = new PlantSelectedEventImpl();

    }

    @Override
    public Plant getSelectedPlant() {
        return stateHelper.getSelectedPlant();
    }
}
