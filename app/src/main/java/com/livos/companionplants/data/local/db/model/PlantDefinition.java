package com.livos.companionplants.data.local.db.model;


import android.support.annotation.NonNull;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.PrimaryKey;

public class PlantDefinition extends RealmObject {
    @PrimaryKey
    private long id;

    private Plant plant;
    private String language;
    private String definition;


    public PlantDefinition() { }

    public PlantDefinition(long id, Plant plant, String language, String definition) {
        this.id = id;
        this.plant = plant;
        this.language = language;
        this.definition = definition;
    }

    @Override
    public String toString() {
        return this.definition;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

}