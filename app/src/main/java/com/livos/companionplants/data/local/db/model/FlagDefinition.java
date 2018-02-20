package com.livos.companionplants.data.local.db.model;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FlagDefinition extends RealmObject {
    @PrimaryKey
    private long id;

    private Flag flag;
    private String language;
    private String definition;


    public FlagDefinition() { }

    public FlagDefinition(long id, Flag flag, String language, String definition) {
        this.id = id;
        this.flag = flag;
        this.language = language;
        this.definition = definition;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) { this.id = id; }

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

    public Flag getFlag() { return flag; }

    public void setFlag(Flag flag) { this.flag = flag; }
}
