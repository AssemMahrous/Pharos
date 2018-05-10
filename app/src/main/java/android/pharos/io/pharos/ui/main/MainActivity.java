package android.pharos.io.pharos.ui.main;

import android.os.Bundle;
import android.pharos.io.pharos.MvpApp;
import android.pharos.io.pharos.R;
import android.pharos.io.pharos.data.AppDataManager;
import android.pharos.io.pharos.data.network.models.City;
import android.pharos.io.pharos.ui.base.BaseActivity;
import android.support.v7.widget.Toolbar;

import java.util.List;

public class MainActivity extends BaseActivity implements MainMvpView {
    MainPresenter presenter;
    int page = 1;
    List<City> cityList;

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

    }

    @Override
    public void addCities(List<City> cities) {
        cityList.addAll(cities);
    }

    @Override
    public void goToMap() {

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
}
