package com.livos.companionplants.main;

import com.livos.companionplants.util.MainScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    @Provides
    @MainScope
    public MainContract.Presenter provideMainPresenter() {
        return new MainPresenter();
    }
}
