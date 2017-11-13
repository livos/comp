package com.livos.companionplants.data.local.database;


import com.livos.companionplants.data.local.database.model.PlantDefinition;
import com.livos.companionplants.model.PlantDetail;
import com.livos.companionplants.model.PlantDetailImpl;

import java.util.List;
import java.util.Locale;

public interface DatabaseHelper {
    PlantDefinition getPlantDefinition(Long id);
    List<PlantDefinition> getAllPlantsDefinitions();

    List<PlantDetail> getAssociatedPlants(Long plantId,String localeCode);
    List<PlantDetail> getAllPlants(String localeCode);
//
    String getPictureName(Long plantId);
}
