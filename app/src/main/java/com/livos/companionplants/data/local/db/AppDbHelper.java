package com.livos.companionplants.data.local.db;


import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.data.local.db.model.PlantDefinition;
import com.livos.companionplants.data.local.db.model.SpaceAssociation;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;

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

    @Override
    public List<Plant> getAssociatedPlants(long plantId) {
        List<Plant> associatedPlants = new ArrayList<>();

        RealmResults<SpaceAssociation> associations = realm
                .where(SpaceAssociation.class)
                .equalTo("plant1.id",plantId).findAll();

        for(SpaceAssociation sa : associations) {
            associatedPlants.add(sa.getPlant2());
        }
        return  associatedPlants;
    }

    @Override
    public Plant getPlantById(long plantId) {
        Plant plant = realm.where(Plant.class).equalTo("id",plantId).findFirst();
        return  plant;
    }


}
