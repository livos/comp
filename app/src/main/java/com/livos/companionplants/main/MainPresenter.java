package com.livos.companionplants.main;


public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;

    @Override
    public void dropView() {
        this.view = null;
    }

    @Override
    public void setView(MainContract.View view) {
        this.view = view;

    }

    @Override
    public void onDrawerOptionAboutClick() {

    }

    @Override
    public void onDrawerRateUsClick() {

    }

    // get data to load in drawer
    @Override
    public void onNavMenuCreated() {
        //getMvpView().updateAppVersion();
    }


}
