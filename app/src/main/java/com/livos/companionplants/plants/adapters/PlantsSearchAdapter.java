package com.livos.companionplants.plants.adapters;



import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.livos.companionplants.R;
import com.livos.companionplants.model.PlantDetail;
import com.livos.companionplants.model.PlantDetailImpl;

import java.util.List;

public class PlantsSearchAdapter extends ArrayAdapter<PlantDetail> {

    private List<PlantDetail> plants;

    public PlantsSearchAdapter(@NonNull Context context, @NonNull List<PlantDetail> plants) {
        super(context, 0, plants);
        this.plants = plants;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PlantDetail plant = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_search, parent, false);
        }

        TextView tvName = convertView.findViewById(R.id.tvName);

        if(plant != null) {
            tvName.setText(plant.getDefinition());
        }

        return convertView;
    }

}
