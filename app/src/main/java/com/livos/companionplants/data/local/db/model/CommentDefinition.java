package com.livos.companionplants.data.local.db.model;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CommentDefinition extends RealmObject {
    @PrimaryKey
    private long id;

    private SpaceAssociation spaceAssociation;
    private String language;
    private String definition;

    public CommentDefinition() { }

    public CommentDefinition(long id, String language, String definition, SpaceAssociation spaceAssociation) {
        this.id = id;
        this.language = language;
        this.definition = definition;
        this.spaceAssociation = spaceAssociation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public SpaceAssociation getSpaceAssociation() {
        return spaceAssociation;
    }

    public void setSpaceAssociation(SpaceAssociation spaceAssociation) {
        this.spaceAssociation = spaceAssociation;
    }
}
