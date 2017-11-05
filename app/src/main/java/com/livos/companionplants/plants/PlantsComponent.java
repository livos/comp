package com.livos.companionplants.plants;

import com.livos.companionplants.application.PlantsApplicationComponent;
import com.livos.companionplants.util.PlantsScope;

import dagger.Component;

@PlantsScope
@Component(dependencies = PlantsApplicationComponent.class, modules = PlantsModule.class)
public interface PlantsComponent {
    void inject(PlantsFragment target);
}
