package com.livos.companionplants.main;

import com.livos.companionplants.util.MainScope;

import dagger.Component;

@MainScope
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity target);
}
