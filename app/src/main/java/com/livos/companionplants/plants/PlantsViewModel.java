package com.livos.companionplants.plants;


import com.livos.companionplants.model.PlantDetailImpl;

import java.util.List;

public class PlantsViewModel {
    private List<PlantDetailImpl> plants;

    public List<PlantDetailImpl> getPlants() {
        return plants;
    }

    public void setPlants(List<PlantDetailImpl> plants) {
        this.plants = plants;
    }

    public void addPlant(PlantDetailImpl plantInfo) {
        this.plants.add(plantInfo);
    }
}
