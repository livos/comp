package com.livos.companionplants.ui.plants;

import com.livos.companionplants.data.local.db.model.Flag;
import com.livos.companionplants.data.local.db.model.Plant;

/**
 * Created by laurent on 2/3/18.
 */

public class AssociatedPlant  {
    private Plant plant;
    private Flag flag;

    public AssociatedPlant(Plant plant, Flag flag) {
        this.plant = plant;
        this.flag = flag;
    }

    public AssociatedPlant(Plant plant) {
        this.plant = plant;
        this.flag = null;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }
}
