package com.livos.companionplants.ui.main;


import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.ui.base.MvpView;

public interface MainMvpView extends MvpView {
    //void toggleTabsVisibility(Plant plant);
    void toggleTab(int tabIdx, int visibility);
    void showCredits();
    void shareApp();
    void closeNavigationDrawer();
    void rateApp();

}
