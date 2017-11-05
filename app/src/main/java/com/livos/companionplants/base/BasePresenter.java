package com.livos.companionplants.base;


public interface BasePresenter<T> {
    void takeView(T view);
    void dropView();
}
