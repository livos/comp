package com.livos.companionplants.ui.search;


import com.livos.companionplants.data.local.db.model.PlantDefinition;
import com.livos.companionplants.ui.base.BasePresenter;
import com.livos.companionplants.ui.base.MvpPresenter;

public interface SearchMvpPresenter <V extends SearchMvpView>
        extends MvpPresenter<V> {

        void onViewPrepared();
        void onListPlantClicked(PlantDefinition plantDefinition);
}
