package com.livos.companionplants.ui.search;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import com.livos.companionplants.R;
import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.data.local.db.model.PlantDefinition;
import com.livos.companionplants.di.component.ActivityComponent;
import com.livos.companionplants.ui.base.BaseFragment;
import com.livos.companionplants.ui.search.adapters.PlantSearchAdapter;
import com.livos.companionplants.ui.events.PlantSelectedEvent;
import com.livos.companionplants.ui.events.PlantSelectedEventImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class SearchFragment extends BaseFragment implements SearchMvpView {
    public static final String TAG = "SearchFragment";

    private PlantSearchAdapter plantSearchAdapter;

    @Inject
    SearchMvpPresenter<SearchMvpView> presenter;

    @BindView(R.id.actv_search)
    AutoCompleteTextView actvSearch;

    @BindView(R.id.civ_plant)
    CircleImageView civPlant;


    public static SearchFragment newInstance() {
        Bundle ars = new Bundle();
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(ars);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            presenter.onAttach(this);
        }
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(Plant event){
        Log.d(TAG,event.getScientificName());
    }

    @Override
    protected void setUp(View view) {

        // A plant has been clicked in the list
        actvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PlantDefinition plantDefinition = (PlantDefinition)adapterView.getAdapter().getItem(i);
                presenter.onListPlantClicked(plantDefinition);
                hideKeyboard();
            }
        });

        presenter.onViewPrepared();
    }


    /**
     * Load plants definitions in adapter
     * @param definitions
     */
    @Override
    public void loadDefinitions(List<PlantDefinition> definitions) {
        plantSearchAdapter = new PlantSearchAdapter(getContext(), 0, definitions);
        actvSearch.setAdapter(plantSearchAdapter);
    }

    @Override
    public void onSelectedPlantChanged(Plant selectedPlant) {
        PlantSelectedEvent plantSelectedEvent = new PlantSelectedEventImpl();
        plantSelectedEvent.setPlant(selectedPlant);
        EventBus.getDefault().post(plantSelectedEvent);
    }

    /**
     * Set plant image and autocomplete texview with current selected plant
     * @param selectedPlant
     */
    @Override
    public void setCurrentPlant(Plant selectedPlant) {
        Resources resources = getContext().getResources();
        int resourceId = resources.getIdentifier(selectedPlant.getPictures().get(0).getPicture(), "drawable",
                getContext().getPackageName());

        String currentPlantName = selectedPlant.getDefinitions().get(0).getDefinition();
        actvSearch.setText(currentPlantName);
        civPlant.setImageDrawable( resources.getDrawable(resourceId));
        actvSearch.setSelection(currentPlantName.length()); // Put the cursor at the end of the name of the new searched plant
        actvSearch.dismissDropDown();
    }


    @Subscribe
    public void onPlantSelectedEvent(PlantSelectedEvent plantSelectedEvent) {

        presenter.onSelectedPlantChanged(plantSelectedEvent);
    }

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
            presenter.onRestoreInstanceState(savedInstanceState);
        }
    }
}
