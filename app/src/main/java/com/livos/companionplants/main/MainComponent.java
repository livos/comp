package com.livos.companionplants.main;

import com.livos.companionplants.util.PerActivityScope;

import dagger.Component;

@PerActivityScope
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity target);
}
