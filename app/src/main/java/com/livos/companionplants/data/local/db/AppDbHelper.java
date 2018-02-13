package com.livos.companionplants.data.local.db;


import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.data.local.db.model.PlantDefinition;
import com.livos.companionplants.data.local.db.model.SpaceAssociation;
import com.livos.companionplants.ui.plants.AssociatedPlant;

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
    public List<AssociatedPlant> getAllPlants() {
        //RealmResults<Plant> plants = realm.where(Plant.class).findAll();
        List<Plant> plants = realm.where(Plant.class).findAll();
        List<AssociatedPlant> associatedPlants = new ArrayList<>();
        for(Plant p:plants) {
            associatedPlants.add(new AssociatedPlant(p));
        }
        return  associatedPlants;
    }

    @Override
    public List<PlantDefinition> getAllDefinitions() {
        List<PlantDefinition> definitions = realm.copyFromRealm(realm.where(PlantDefinition.class).findAll());
        return definitions;
    }

    @Override
    public List<AssociatedPlant> getAssociatedPlants(long plantId) {
        List<AssociatedPlant> associatedPlants = new ArrayList<>();

        RealmResults<SpaceAssociation> associations = realm
                .where(SpaceAssociation.class)
                .equalTo("plant1.id",plantId).findAll();

        for(SpaceAssociation sa : associations) {
            associatedPlants.add(new AssociatedPlant(sa.getPlant2(),sa.getFlag()));
        }
        return  associatedPlants;
    }

    @Override
    public List<AssociatedPlant> getAssociatedPlantsHelps(long plantId) {
        List<AssociatedPlant> associatedPlants = new ArrayList<>();

        RealmResults<SpaceAssociation> associations = realm
                .where(SpaceAssociation.class)
                .equalTo("plant1.id",plantId)
                .and()
                .equalTo("flag.id", 1).findAll();

        for(SpaceAssociation sa : associations) {
            associatedPlants.add(new AssociatedPlant(sa.getPlant2(),sa.getFlag()));
        }
        return  associatedPlants;
    }

    @Override
    public List<AssociatedPlant> getAssociatedPlantsHelpedBy(long plantId) {
        List<AssociatedPlant> associatedPlants = new ArrayList<>();

        RealmResults<SpaceAssociation> associations = realm
                .where(SpaceAssociation.class)
                .equalTo("plant1.id",plantId)
                .and()
                .equalTo("flag.id", 2).findAll();

        for(SpaceAssociation sa : associations) {
            associatedPlants.add(new AssociatedPlant(sa.getPlant2(),sa.getFlag()));
        }
        return  associatedPlants;
    }

    @Override
    public List<AssociatedPlant> getAssociatedPlantsAvoid(long plantId) {
        List<AssociatedPlant> associatedPlants = new ArrayList<>();

        RealmResults<SpaceAssociation> associations = realm
                .where(SpaceAssociation.class)
                .equalTo("plant1.id",plantId)
                .and()
                .equalTo("flag.id", 5).findAll();

        for(SpaceAssociation sa : associations) {
            associatedPlants.add(new AssociatedPlant(sa.getPlant2(),sa.getFlag()));
        }
        return  associatedPlants;
    }

    @Override
    public List<AssociatedPlant> getAssociatedPlantsNeutral(long plantId) {
        List<AssociatedPlant> associatedPlants = new ArrayList<>();

        RealmResults<SpaceAssociation> associations = realm
                .where(SpaceAssociation.class)
                .equalTo("plant1.id",plantId)
                .and()
                .equalTo("flag.id", 6).findAll();

        for(SpaceAssociation sa : associations) {
            associatedPlants.add(new AssociatedPlant(sa.getPlant2(),sa.getFlag()));
        }
        return  associatedPlants;
    }

    @Override
    public Plant getPlantById(long plantId) {
        Plant plant = realm.where(Plant.class).equalTo("id",plantId).findFirst();
        return  plant;
    }

    @Override
    public Plant getPlantByName(String name) {
        PlantDefinition plantDefinition = realm.where(PlantDefinition.class).equalTo("definition", name).findFirst();
        Plant plant = realm.where(Plant.class).equalTo("id", plantDefinition.getId()).findFirst();
        return plant;
    }
}
