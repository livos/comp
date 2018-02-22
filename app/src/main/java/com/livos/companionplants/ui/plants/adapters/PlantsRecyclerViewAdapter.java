package com.livos.companionplants.ui.plants.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.livos.companionplants.R;
import com.livos.companionplants.data.local.db.model.Flag;
import com.livos.companionplants.ui.plants.AssociatedPlant;
import com.livos.companionplants.utils.CastHelper;

import java.util.List;

public class PlantsRecyclerViewAdapter extends RecyclerView.Adapter<PlantsRecyclerViewHolder>  {
    private Context context;
    private List<AssociatedPlant> plants;

    public PlantsRecyclerViewAdapter(Context context) {
        this.context = context;
    }


    public void updateAssociatedPlants(List<AssociatedPlant> plants) {
        this.plants = plants;

    }

    @Override
    public PlantsRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_list_item, null);
        return new PlantsRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlantsRecyclerViewHolder holder, int position) {
        int resourceId = context.getResources().getIdentifier(plants.get(position).getPlant().getPictures().get(0).getPicture(),"drawable", context.getPackageName());
        holder.getIvPlant().setImageResource(resourceId);
        holder.getTvPlantName().setText(plants.get(position).getPlant().getDefinitions().get(0).getDefinition());

        holder.getIvPlant().setTag(plants.get(position));
        holder.getTvPlantName().setBackgroundColor(getFlagColor(0L)); // Default flag is neutral
        Flag plantFlag = plants.get(position).getFlag();
        if(plantFlag != null) {
            holder.getTvPlantName().setBackgroundColor(getFlagColor(plants.get(position).getFlag().getId()));
        }
    }

    @Override
    public int getItemCount() {
        int itemCount = 0;
        if(plants != null) {
            itemCount = plants.size();
        }
        return itemCount;
    }

    public int getFlagColor(Long flagId) {
        switch (CastHelper.safeLongToInt(flagId)) {
            case 1 :
                return context.getResources().getColor(R.color.pale_green); //helps
            case 2:
                return context.getResources().getColor(R.color.dark_green); //helped by
            case 3:
                return context.getResources().getColor(R.color.colorNeutral); //attracts
            case 4:
                return context.getResources().getColor(R.color.colorNeutral); //repels or distract
            case 5:
                return context.getResources().getColor(R.color.colorBad); //avoid
            case 6:
                return context.getResources().getColor(R.color.colorNeutral); //neutral
            default:
                return context.getResources().getColor(R.color.colorNeutral);
        }
    }
}
