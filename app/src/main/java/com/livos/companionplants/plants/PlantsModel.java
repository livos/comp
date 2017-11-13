package com.livos.companionplants.plants;


import com.livos.companionplants.data.local.database.DatabaseHelper;
import com.livos.companionplants.data.local.database.model.PlantDefinition;
import com.livos.companionplants.model.PlantDetail;
import com.livos.companionplants.model.PlantDetailImpl;

import java.util.List;
import java.util.Locale;

public class PlantsModel implements PlantsContract.Model {
    private DatabaseHelper databaseHelper;

    public PlantsModel(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }


//    @Override
//    public List<PlantAssociation> getAllPlants() {
//        //return databaseHelper.getAllPlants();
//        return null;
//    }

    @Override
    public List<PlantDetail> getAssociatedPlants(Long plantId, String localeCode) {
        return databaseHelper.getAssociatedPlants(plantId, localeCode);
    }

    @Override
    public List<PlantDetail> getAllPlants(String localeCode) {
        return databaseHelper.getAllPlants(localeCode);
    }
//
//    @Override
//    public Long getDefaultPlantId() {
//        return 1L; // Id 1 => Ail
//    }

    @Override
    public List<PlantDefinition> getAllPlantsDefinitions() {
        List<PlantDefinition> plants = databaseHelper.getAllPlantsDefinitions();

        return plants;
    }

    @Override
    public String getPicturename(Long plantId) {
        String pictureName = databaseHelper.getPictureName(plantId);
        return pictureName;
    }
}
