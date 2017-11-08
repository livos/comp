package com.livos.companionplants.model;


public class PlantDetailImpl implements PlantDetail {
    private Long plantId;
    private String definition;
    private String picture;
    private int flag;


    public PlantDetailImpl(Long plantId, String definition, String picture, int flag) {
        this.plantId = plantId;
        this.definition = definition;
        this.picture = picture;
        this.flag = flag;
    }

    @Override
    public String toString() {
        return this.definition;
    }

    @Override
    public Long getPlantId() {
        return plantId;
    }

    @Override
    public void setPlantId(Long plantId) {
        this.plantId = plantId;
    }

    @Override
    public String getDefinition() {
        return definition;
    }

    @Override
    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    public String getPicture() {
        return picture;
    }

    @Override
    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public int getFlag() {
        return flag;
    }

    @Override
    public void setFlag(int flag) {
        this.flag = flag;
    }
}
