package com.livos.companionplants.ui.search;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import com.livos.companionplants.R;
import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.data.local.db.model.PlantDefinition;
import com.livos.companionplants.di.component.ActivityComponent;
import com.livos.companionplants.ui.base.BaseFragment;
import com.livos.companionplants.ui.search.adapters.PlantSearchAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchFragment extends BaseFragment implements SearchMvpView {
    public static final String TAG = "SearchFragment";

    private PlantSearchAdapter plantSearchAdapter;

    @Inject
    SearchMvpPresenter<SearchMvpView> presenter;

    @BindView(R.id.actv_search)
    AutoCompleteTextView actvSearch;




    public static SearchFragment newInstance() {
        Bundle ars = new Bundle();
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(ars);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

    }

    @Override
    public void onStop() {
        super.onStop();
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
        presenter.onViewPrepared();
    }

    @Override
    public void loadDefinitions(List<PlantDefinition> definitions) {
        plantSearchAdapter = new PlantSearchAdapter(getContext(), 0, definitions);
        actvSearch.setAdapter(plantSearchAdapter);
    }

}
