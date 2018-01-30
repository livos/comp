package com.livos.companionplants.data.local.db.model;


import android.support.annotation.NonNull;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.PrimaryKey;

public class Plant extends RealmObject  {
    @PrimaryKey
    private long id;
    private String scientificName;
    private Date lastUpdate;
    @LinkingObjects("plant")
    private final RealmResults<PlantDefinition> definitions =  null;
    @LinkingObjects("plant")
    private final RealmResults<Picture> pictures =  null;


    public Plant() { }

    public Plant(long id, String scientificName, Date lastUpdate) {
        this.id = id;
        this.scientificName = scientificName;
        this.lastUpdate = lastUpdate;
    }

    public RealmResults<PlantDefinition> getDefinitions() {
        return definitions;
    }

    public RealmResults<Picture> getPictures() {
        return pictures;
    }

    public long getId() { return id; }

    public void setId(long id) {
        this.id = id;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}
