package com.livos.companionplants.plants;


import com.livos.companionplants.model.PlantDetail;

import java.util.List;

public class PlantsViewModel {
    private List<PlantDetail> plants;

    public List<PlantDetail> getPlants() {
        return plants;
    }

    public void setPlants(List<PlantDetail> plants) {
        this.plants = plants;
    }

    public void addPlant(PlantDetail plantInfo) {
        this.plants.add(plantInfo);
    }
}
