package com.livos.companionplants.model;


public interface PlantDetail {
    @Override
    String toString();

    Long getPlantId();

    void setPlantId(Long plantId);

    String getDefinition();

    void setDefinition(String definition);

    String getPicture();

    void setPicture(String picture);

    int getFlag();

    void setFlag(int flag);
}
