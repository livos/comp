package com.livos.companionplants.plants.adapters;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.livos.companionplants.R;
import com.livos.companionplants.events.PlantSelectedEvent;
import com.livos.companionplants.model.PlantDetail;
import com.livos.companionplants.model.PlantDetailImpl;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private List<PlantDetail> associatedPlants;
    Context context;
    PlantSelectedEvent plantSelectedEvent;

    public RecyclerViewAdapter(Context context, List<PlantDetail> associatedPlants, PlantSelectedEvent plantSelectedEvent) {
        this.associatedPlants = associatedPlants;
        this.context = context;
        this.plantSelectedEvent = plantSelectedEvent;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_list_item, null);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view, plantSelectedEvent);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        int resourceId = context.getResources().getIdentifier(associatedPlants.get(position).getPicture(),"drawable", context.getPackageName());
        holder.getIvPlant().setImageResource(resourceId);
        holder.getTvPlantName().setText(associatedPlants.get(position).getDefinition());
        int flagId = associatedPlants.get(position).getFlag();

        holder.getTvPlantName().setBackgroundColor(getFlagColor(flagId));
        holder.getIvPlant().setTag(associatedPlants.get(position));
        holder.setPlantAssociation(associatedPlants.get(position));
    }

    public int getFlagColor(int flagId) {
        switch (flagId) {
            case 1 :
                return context.getResources().getColor(R.color.pale_green);
            case 2:
                return context.getResources().getColor(R.color.colorNeutral);
            case 3:
                return context.getResources().getColor(R.color.colorDebate);
            case 4:
                return context.getResources().getColor(R.color.colorBad);
            case 5:
                return context.getResources().getColor(R.color.colorNeutral);
            default:
                return context.getResources().getColor(R.color.colorNeutral);
        }
    }

    @Override
    public int getItemCount() {
        return associatedPlants.size();
    }
}
