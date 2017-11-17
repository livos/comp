package com.livos.companionplants.plants;

import com.livos.companionplants.application.PlantsApplicationComponent;
import com.livos.companionplants.util.PerFragmentScope;

import dagger.Component;

@PerFragmentScope
@Component(dependencies = PlantsApplicationComponent.class, modules = PlantsModule.class)
public interface PlantsComponent {
    void inject(PlantsFragment target);
}
