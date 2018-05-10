package android.pharos.io.pharos;

import android.app.Application;
import android.pharos.io.pharos.data.AppDataManager;
import android.pharos.io.pharos.data.db.CityDataBase;
import android.pharos.io.pharos.data.network.ApiModule;

public class MvpApp extends Application {

    AppDataManager dataManager;

    @Override
    public void onCreate() {
        super.onCreate();
        CityDataBase cityDataBase = CityDataBase.getInstance(getApplicationContext());
        ApiModule apiModule = new ApiModule();
        dataManager = new AppDataManager(cityDataBase.cityDao(),
                apiModule.provideApiService());

    }


    public AppDataManager getDataManager() {
        return dataManager;
    }
}
