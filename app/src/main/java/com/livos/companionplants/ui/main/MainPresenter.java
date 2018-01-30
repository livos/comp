package com.livos.companionplants.ui.main;


import com.livos.companionplants.data.DataManager;
import com.livos.companionplants.ui.base.BasePresenter;
import com.livos.companionplants.ui.base.MvpPresenter;
import com.livos.companionplants.ui.base.MvpView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MainPresenter<V extends MvpView> extends BasePresenter implements MainMvpPresenter {

    @Inject
    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }


}
