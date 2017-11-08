package com.livos.companionplants.data.local.database;


import com.livos.companionplants.data.local.database.model.PlantDefinition;
import com.livos.companionplants.model.PlantDetail;
import com.livos.companionplants.model.PlantDetailImpl;

import java.util.List;

public interface DatabaseHelper {
    PlantDefinition getPlantDefinition(Long id);
    List<PlantDefinition> getAllPlantsDefinitions();

    List<PlantDetail> getAssociatedPlants(Long plantId);
    List<PlantDetail> getAllPlants();
//
    String getPictureName(Long plantId);
}
