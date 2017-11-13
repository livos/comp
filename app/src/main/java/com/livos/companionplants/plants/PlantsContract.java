package com.livos.companionplants.plants;


import android.content.Context;
import android.os.Bundle;

import com.livos.companionplants.base.BasePresenter;
import com.livos.companionplants.data.local.database.model.PlantDefinition;
import com.livos.companionplants.events.PlantSelectedEvent;
import com.livos.companionplants.model.PlantDetail;
import com.livos.companionplants.model.PlantDetailImpl;

import java.util.List;
import java.util.Locale;

public interface PlantsContract {
    interface View {
        void updateData(List<PlantDetail> plantsAssociations, PlantSelectedEvent event);
        void updatePlantsList(List<PlantDetail> plantsDefinitions);
        void updatePlantsGrid(List<PlantDetail> plants, PlantSelectedEvent plantSelectedEvent);
    }

    interface Presenter extends BasePresenter<View> {
        void takeView(PlantsContract.View view);
        void dropView();
        void onEvent(PlantSelectedEvent event);
        void onSaveInstanceState(Bundle outState);
        void onRestoreInstanceState(Bundle savedInstanceState);
        void loadData(String localeCode);
        void onListItemClicked(PlantDetailImpl plant, Context context);
    }

    interface Model {
        //List<PlantAssociation> getAllPlants();
        List<PlantDetail>  getAssociatedPlants(Long plantId, String localeCode);
        //Long getDefaultPlantId();
        List<PlantDefinition> getAllPlantsDefinitions();
        String getPicturename(Long plantId);
        List<PlantDetail> getAllPlants(String localeCode);
    }
}
