package com.livos.companionplants.data.local.db.model;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.PrimaryKey;

public class Flag  extends RealmObject {
    @PrimaryKey
    private long id;
    @LinkingObjects("flag")
    private final RealmResults<FlagDefinition> definitions =  null;

    public Flag() { }

    public Flag(long id) {
        this.id = id;
    }

    public RealmResults<FlagDefinition> getDefinitions() {
        return definitions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
