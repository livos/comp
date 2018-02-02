package com.livos.companionplants.ui.plants.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.livos.companionplants.R;
import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.ui.plants.PlantsMvpPresenter;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>  {
    private Context context;
    private List<Plant> plants;

    public RecyclerViewAdapter(Context context, List<Plant> plants) {
        this.context = context;
        this.plants = plants;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_list_item, null);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        int resourceId = context.getResources().getIdentifier(plants.get(position).getPictures().get(0).getPicture(),"drawable", context.getPackageName());
        holder.getIvPlant().setImageResource(resourceId);
        holder.getTvPlantName().setText(plants.get(position).getDefinitions().get(0).getDefinition());
        holder.getIvPlant().setTag(plants.get(position));
    }

    @Override
    public int getItemCount() {

        return plants.size();
    }
}
