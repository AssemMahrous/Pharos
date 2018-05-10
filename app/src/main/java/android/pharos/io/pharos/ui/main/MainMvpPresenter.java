package android.pharos.io.pharos.ui.main;

import android.pharos.io.pharos.data.network.models.City;
import android.pharos.io.pharos.ui.base.MvpPresenter;

import java.util.List;

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void getCities(int page);

    void setCities(List<City> cities);

    void endPagination();

    void incrementPage();

    void insertDataBase(List<City> cities);

    void determineCities(List<City> cities);
}
