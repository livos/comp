package com.livos.companionplants.main;

import com.livos.companionplants.util.PerActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    @Provides
    @PerActivityScope
    public MainContract.Presenter provideMainPresenter() {
        return new MainPresenter();
    }
}
