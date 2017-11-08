package com.livos.companionplants.plants;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import com.livos.companionplants.R;
import com.livos.companionplants.application.PlantsApplication;
import com.livos.companionplants.events.PlantSelectedEvent;
import com.livos.companionplants.model.PlantDetail;
import com.livos.companionplants.model.PlantDetailImpl;
import com.livos.companionplants.plants.adapters.PlantsSearchAdapter;
import com.livos.companionplants.plants.adapters.RecyclerViewAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.livos.companionplants.util.KeyboardUtil.hideSoftKeyboard;

public class PlantsFragment extends Fragment implements PlantsContract.View {
    private static final String TAG = PlantsFragment.class.getSimpleName();

    private GridLayoutManager gridLayoutManager;
    private Long plantSelectedId;


    @Inject
    PlantsContract.Presenter presenter;

    @BindView(R.id.rv_plants)
    RecyclerView grvPlants;

    @BindView(R.id.actv_search)
    AutoCompleteTextView actvSearch;

    @BindView(R.id.civ_plant)
    CircleImageView civPlant;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plants,container,false);
        ButterKnife.bind(this, view);

        // Increase recyclerview performance
        grvPlants.setHasFixedSize(true);

        gridLayoutManager = new GridLayoutManager(getContext(), 4);
        grvPlants.setLayoutManager(gridLayoutManager);


        // Item clicked in the AutocompleteTextView list
        actvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PlantDetailImpl plant = (PlantDetailImpl)adapterView.getAdapter().getItem(i);

                hideSoftKeyboard(getContext());

                presenter.onListItemClicked(plant, getContext());
            }
        });

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        PlantsComponent plantsComponent = ((PlantsApplication)getActivity().getApplication()).getPlantsComponent();
        plantsComponent.inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.takeView(this);
        presenter.loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }

    private void loadGrid() {

    }

    @Override
    public void updateData(List<PlantDetail> associatedPlants, PlantSelectedEvent plantSelectedEvent) {
        // Update grid data

        plantSelectedId = plantSelectedEvent.getPlantId();

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),associatedPlants, plantSelectedEvent);
        grvPlants.setAdapter(recyclerViewAdapter);

        // Update search part
        String plantName = plantSelectedEvent.getPlantName();
        actvSearch.setText(plantName);
        civPlant.setImageDrawable(plantSelectedEvent.getImage());
        actvSearch.setSelection(plantName.length()); // Put the cursor at the end of the name of the new searched plant
        actvSearch.dismissDropDown();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(outState != null) {
            presenter.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null) {

            presenter.takeView(this);
            presenter.onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    public void updatePlantsList(List<PlantDetail> plants) {
        PlantsSearchAdapter adapter = new PlantsSearchAdapter(getContext(), plants);
        actvSearch.setAdapter(adapter);
    }

    @Override
    public void updatePlantsGrid(List<PlantDetail> plants) {


    }



    public interface OnPlantSelectedListener {
        void onPlantSelected(Long plantId);
    }
}
