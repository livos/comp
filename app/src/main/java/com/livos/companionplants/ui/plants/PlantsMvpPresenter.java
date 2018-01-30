package com.livos.companionplants.ui.plants;


import com.livos.companionplants.ui.base.MvpPresenter;

public interface PlantsMvpPresenter<V extends PlantsMvpView>
    extends MvpPresenter<V> {

    void onViewPrepared();

}
