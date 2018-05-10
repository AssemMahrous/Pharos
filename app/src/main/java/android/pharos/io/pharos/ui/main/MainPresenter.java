package android.pharos.io.pharos.ui.main;

import android.pharos.io.pharos.data.AppDataManager;
import android.pharos.io.pharos.data.network.models.City;
import android.pharos.io.pharos.ui.base.BasePresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {

    public MainPresenter(AppDataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getCities(int page) {
        getDataManager()
                .getApi()
                .getCities(String.valueOf(page))
                .enqueue(new Callback<List<City>>() {
                    @Override
                    public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                        try {
                            if (response.isSuccessful()) {
                                determineCities(response.body());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<City>> call, Throwable t) {

                    }
                });
    }

    @Override
    public void setCities(List<City> cities) {
        getMvpView().addCities(cities);
        insertDataBase(cities);
    }

    @Override
    public void endPagination() {
        getMvpView().endPagination();
    }

    @Override
    public void incrementPage() {
        getMvpView().incrementPage();
    }

    @Override
    public void insertDataBase(List<City> cities) {
        for (int i = 0; i < cities.size(); i++)
            getDataManager().insertCity(cities.get(i));
    }

    @Override
    public void determineCities(List<City> cities) {
        if (cities.size() == 50) incrementPage();
        else endPagination();

        setCities(cities);
    }
}
