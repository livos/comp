package com.livos.companionplants.data.local.database;


public class PlantAssociation {
    private Long _id;
    private Long plantId1;
    private String plantDefinition1;
    private String picture1;
    private Long plantId2;
    private String plantDefinition2;
    private String picture2;

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Long getPlantId1() {
        return plantId1;
    }

    public void setPlantId1(Long plantId1) {
        this.plantId1 = plantId1;
    }

    public String getPlantDefinition1() {
        return plantDefinition1;
    }

    public void setPlantDefinition1(String plantDefinition1) {
        this.plantDefinition1 = plantDefinition1;
    }

    public String getPicture1() {
        return picture1;
    }

    public void setPicture1(String picture1) {
        this.picture1 = picture1;
    }

    public Long getPlantId2() {
        return plantId2;
    }

    public void setPlantId2(Long plantId2) {
        this.plantId2 = plantId2;
    }

    public String getPlantDefinition2() {
        return plantDefinition2;
    }

    public void setPlantDefinition2(String plantDefinition2) {
        this.plantDefinition2 = plantDefinition2;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    public Long getFlagId() {
        return flagId;
    }

    public void setFlagId(Long flagId) {
        this.flagId = flagId;
    }

    private Long flagId;
}