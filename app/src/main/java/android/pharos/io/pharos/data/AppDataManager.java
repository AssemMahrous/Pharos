package android.pharos.io.pharos.data;

import android.os.AsyncTask;
import android.pharos.io.pharos.data.db.CityDao;
import android.pharos.io.pharos.data.db.CityEntry;
import android.pharos.io.pharos.data.network.PharosApi;
import android.pharos.io.pharos.data.network.models.City;

public class AppDataManager {

    private final CityDao cityDao;
    private final PharosApi pharosApi;

    public AppDataManager(CityDao cityDao,
                          PharosApi pharosApi) {
        this.cityDao = cityDao;
        this.pharosApi = pharosApi;
    }

    public PharosApi getApi() {
        return pharosApi;
    }


    public void insertCity(City payloads) {
        new insertCityAsync(cityDao).execute(payloads);
    }

    static class insertCityAsync extends AsyncTask<City, Void, Void> {

        private CityDao mCityDoa;

        insertCityAsync(CityDao cityDao) {
            mCityDoa = cityDao;

        }


        @Override
        protected final Void doInBackground(City[] lists) {
            CityEntry weatherEntry = new CityEntry(lists[0]);
            mCityDoa.bulkInsert(weatherEntry);
            return null;
        }
    }
}
