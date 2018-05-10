package android.pharos.io.pharos.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.pharos.io.pharos.MvpApp;
import android.pharos.io.pharos.R;
import android.pharos.io.pharos.data.AppDataManager;
import android.pharos.io.pharos.data.network.models.City;
import android.pharos.io.pharos.ui.base.BaseActivity;
import android.pharos.io.pharos.ui.main.adapter.CityAdapter;
import android.pharos.io.pharos.ui.main.util.RecyclerViewEmptySupport;
import android.pharos.io.pharos.ui.map.MapsActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements MainMvpView {
    MainPresenter presenter;
    int page = 1;
    List<City> cityList = new ArrayList<>();
    boolean isLoading;
    private int visibleThreshold = 5;
    int totalItemCount, lastVisibleItem;
    private LinearLayoutManager mLayoutManager;
    CityAdapter cityAdapter;
    String query = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AppDataManager dataManager = ((MvpApp) getApplication()).getDataManager();
        presenter = new MainPresenter(dataManager);
        presenter.onAttach(this);
        setViews();
    }

    @Override
    public void setViews() {
        cityAdapter = new CityAdapter(this);
        mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        RecyclerViewEmptySupport cities_recycler = findViewById(R.id.cities_recycler);
        cities_recycler.setLayoutManager(mLayoutManager);
        cities_recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (page != 0 && query.equals("")) {
                    totalItemCount = mLayoutManager.getItemCount();
                    lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();

                    if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        getCities();
                        isLoading = true;
                    }
                }
            }
        });
        cities_recycler.setAdapter(cityAdapter);

        cities_recycler.setEmptyView(findViewById(R.id.empty_view));

        getCities();

        TextView search_input = findViewById(R.id.search_input);
        search_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                query = s.toString();
                final List<City> filteredModelList = filter(cityList, query);
                cityAdapter.replaceAll(filteredModelList);
            }
        });
    }

    @Override
    public void addCities(List<City> cities) {
        cityList.addAll(cities);
        cityAdapter.addAll(cityList);
        isLoading = false;
    }

    @Override
    public void goToMap(String lat, String lng, String name) {
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        intent.putExtra("lat", lat);
        intent.putExtra("lng", lng);
        intent.putExtra("name", name);
        startActivity(intent);
    }

    @Override
    public void incrementPage() {
        page++;
    }

    @Override
    public void endPagination() {
        page = 0;
    }

    @Override
    public void getCities() {
        presenter.getCities(page);
    }


    private static List<City> filter(List<City> models, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<City> filteredModelList = new ArrayList<>();
        for (City model : models) {
            final String text = model.getName().toLowerCase();
            if (text.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }
}
