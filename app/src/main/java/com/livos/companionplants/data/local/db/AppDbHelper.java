package com.livos.companionplants.data.local.db;


import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.data.local.db.model.PlantDefinition;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

public class AppDbHelper implements DbHelper {
    Realm realm;

    @Inject
    public AppDbHelper(Realm realm) {
        this.realm = realm;

    }

    @Override
    public List<Plant> getAllPlants() {
        //RealmResults<Plant> plants = realm.where(Plant.class).findAll();
        List<Plant> plants = realm.where(Plant.class).findAll();
        return  plants;
    }

    @Override
    public List<PlantDefinition> getAllDefinitions() {
        List<PlantDefinition> definitions = realm.copyFromRealm(realm.where(PlantDefinition.class).findAll());
        return definitions;
    }
}
