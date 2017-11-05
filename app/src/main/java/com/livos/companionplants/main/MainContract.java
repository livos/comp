package com.livos.companionplants.main;


public interface MainContract {
    interface View {

    }

    interface Presenter {
        void dropView();
        void setView(MainContract.View view);
    }
}
