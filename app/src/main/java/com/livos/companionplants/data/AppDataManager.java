package com.livos.companionplants.data;


import com.livos.companionplants.data.local.db.DbHelper;
import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.data.local.db.model.PlantDefinition;
import com.livos.companionplants.data.local.prefs.PreferencesHelper;

import java.util.List;

import javax.inject.Inject;

public class AppDataManager implements DataManager {
    private final DbHelper dbHelper;

    @Inject
    public AppDataManager(DbHelper dbHelper, PreferencesHelper preferencesHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public List<Plant> getAllPlants() {
        return dbHelper.getAllPlants();
    }

    @Override
    public List<PlantDefinition> getAllDefinitions() {
        return dbHelper.getAllDefinitions();
    }
}
