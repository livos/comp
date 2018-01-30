package com.livos.companionplants.data.local.db.model;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Picture extends RealmObject {
    @PrimaryKey
    private long id;

    private Plant plant;
    private String picture;

    public Picture () { }

    public Picture(long id, Plant plant, String picture) {
        this.id = id;
        this.plant = plant;
        this.picture = picture;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
