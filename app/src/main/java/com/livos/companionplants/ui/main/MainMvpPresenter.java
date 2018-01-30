package com.livos.companionplants.ui.main;


import com.livos.companionplants.di.scopes.PerActivity;
import com.livos.companionplants.ui.base.MvpPresenter;
import com.livos.companionplants.ui.base.MvpView;

@PerActivity
public interface MainMvpPresenter<V extends MvpView> extends MvpPresenter<V> {


}
