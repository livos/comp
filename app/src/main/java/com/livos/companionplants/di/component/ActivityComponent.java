package com.livos.companionplants.di.component;

import com.livos.companionplants.di.module.ActivityModule;
import com.livos.companionplants.di.scopes.PerActivity;
import com.livos.companionplants.ui.main.MainActivity;
import com.livos.companionplants.ui.plants.PlantsFragment;
import com.livos.companionplants.ui.search.SearchFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);
    void inject(PlantsFragment fragment);
    void inject(SearchFragment fragment);
}
