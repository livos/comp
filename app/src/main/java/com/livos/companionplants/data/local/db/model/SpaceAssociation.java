package com.livos.companionplants.data.local.db.model;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.PrimaryKey;

public class SpaceAssociation  extends RealmObject {
    @PrimaryKey
    private long id;
    private Plant plant1;
    private Plant plant2;
    private Flag flag;
    @LinkingObjects("spaceAssociation")
    private final RealmResults<CommentDefinition> commentDefinitions =  null;

    public SpaceAssociation() { }

    public SpaceAssociation(long id, Plant plant1, Plant plant2, Flag flag) {
        this.id = id;
        this.plant1 = plant1;
        this.plant2 = plant2;
        this.flag = flag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Plant getPlant1() {
        return plant1;
    }

    public void setPlant1(Plant plant1) {
        this.plant1 = plant1;
    }

    public Plant getPlant2() {
        return plant2;
    }

    public void setPlant2(Plant plant2) {
        this.plant2 = plant2;
    }

    public Flag getFlag() { return flag; }

    public void setFlag(Flag flag) { this.flag = flag; }

    public RealmResults<CommentDefinition> getCommentDefinitions() {
        return commentDefinitions;
    }
}