package com.livos.companionplants.model;


public class PlantDetail {
    private Long plantId;
    private String definition;
    private String picture;
    private int flag;

    public PlantDetail(Long plantId, String definition, String picture, int flag) {
        this.plantId = plantId;
        this.definition = definition;
        this.picture = picture;
        this.flag = flag;
    }

    @Override
    public String toString() {
        return this.definition;
    }

    public Long getPlantId() {
        return plantId;
    }

    public void setPlantId(Long plantId) {
        this.plantId = plantId;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
