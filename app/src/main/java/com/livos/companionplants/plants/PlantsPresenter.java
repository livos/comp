package com.livos.companionplants.plants;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import com.livos.companionplants.events.PlantSelectedEvent;
import com.livos.companionplants.model.PlantDetail;
import com.livos.companionplants.model.PlantDetailImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import javax.inject.Inject;


public class PlantsPresenter implements PlantsContract.Presenter {
    private static final String TAG = PlantsPresenter.class.getSimpleName();
    private PlantsContract.View view;
    private PlantsContract.Model plantsModel;
    private PlantSelectedEvent plantSelectedEvent;
    private Long plantSelectedId;

    @Inject
    Context context;


    public PlantsPresenter(PlantsContract.Model plantsModel, PlantSelectedEvent plantSelectedEvent) {
        this.plantsModel = plantsModel;
        this.plantSelectedEvent = plantSelectedEvent;
    }


    @Override
    public void takeView(PlantsContract.View view) {
        this.view = view;
        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void dropView() {
        this.view = null;
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    // Event fired when a plant has been clicked in SearchFragment AutocompleteTextView or
    // in the grid
    @Override
    @Subscribe
    public void onEvent(PlantSelectedEvent plantSelectedEvent) {
        plantSelectedId = plantSelectedEvent.getPlantId();
        updateView(plantSelectedId);
    }

    @Override
    public void onRestoreInstanceState (Bundle savedInstanceState) {
        plantSelectedId = savedInstanceState.getLong("plantSelectedId");
        updateView(plantSelectedId);
    }

    // Load data in view
    private void updateView(Long plantId) {
        List<PlantDetail> plantAssociations = plantsModel.getAssociatedPlants(plantId);
        //view.updateSearchedPlant(plantSelectedEvent.getPlantName(),plantSelectedEvent.getImage());
        view.updateData(plantAssociations, plantSelectedEvent);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        if(outState != null && plantSelectedId != null) {
            outState.putLong("plantSelectedId", plantSelectedId);
        }
    }

    @Override
    public void loadData() {
        List<PlantDetail> plants = plantsModel.getAllPlants();
        view.updatePlantsList(plants);
        view.updatePlantsGrid(plants);
    }

    // Fired when a plant is clicked in the autocompletetextview
    @Override
    public void onListItemClicked(PlantDetailImpl plant, Context context) {
        plantSelectedEvent.setPlantId(plant.getPlantId());
        plantSelectedEvent.setPlantName(plant.getDefinition());

        Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(plantsModel.getPicturename(plant.getPlantId()), "drawable", context.getPackageName());
        plantSelectedEvent.setImage(resources.getDrawable(resourceId));

        EventBus.getDefault().post(plantSelectedEvent);
    }



}
