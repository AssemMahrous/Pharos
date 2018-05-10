package android.pharos.io.pharos.ui.main;

import android.pharos.io.pharos.data.network.models.City;
import android.pharos.io.pharos.ui.base.MvpView;

import java.util.List;

public interface MainMvpView extends MvpView {
    void setViews();

    void addCities(List<City> cities);

    void goToMap(String lat,String lng,String name);

    void incrementPage();

    void endPagination();

    void getCities();


}
