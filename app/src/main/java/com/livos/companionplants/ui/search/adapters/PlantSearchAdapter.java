package com.livos.companionplants.ui.search.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.livos.companionplants.R;
import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.data.local.db.model.PlantDefinition;

import java.util.List;

/**
 * Created by laurent on 1/15/18.
 */

public class PlantSearchAdapter extends ArrayAdapter<PlantDefinition> {
    private List<PlantDefinition> plants;
    private Context context;
    private int resource;

    public PlantSearchAdapter(@NonNull Context context, int resource, @NonNull List<PlantDefinition> plants) {
        super(context, resource, plants);
        this.plants = plants;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        PlantDefinition plant = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_search, parent, false);
        }

        TextView tvName = view.findViewById(R.id.tvPlantName);

        if(plant != null) {
            tvName.setText(plant.getDefinition());
        }

        return view;
    }
}
