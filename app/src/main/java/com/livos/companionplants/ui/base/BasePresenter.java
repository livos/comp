package com.livos.companionplants.ui.base;


import com.livos.companionplants.data.DataManager;

import javax.inject.Inject;


public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private final DataManager dataManager;
    private V mvpView;

    @Inject
    public BasePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public DataManager getDataManager() {
        return dataManager;
    }


    @Override
    public void onAttach(V mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mvpView = null;
    }

    public V getMvpView() {
        return mvpView;
    }
}
