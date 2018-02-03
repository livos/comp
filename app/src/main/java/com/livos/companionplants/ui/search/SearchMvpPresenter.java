package com.livos.companionplants.ui.search;


import com.livos.companionplants.data.local.db.model.PlantDefinition;
import com.livos.companionplants.ui.base.MvpPresenter;
import com.livos.companionplants.ui.events.PlantSelectedEvent;

public interface SearchMvpPresenter <V extends SearchMvpView>
        extends MvpPresenter<V> {

        void onViewPrepared();
        void onListPlantClicked(PlantDefinition plantDefinition);
        void onSelectedPlantChanged(PlantSelectedEvent plantSelectedEvent);
}
