package android.pharos.io.pharos.data.network;

import android.pharos.io.pharos.data.network.models.City;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PharosApi {

    @GET("cities.json")
    Call<List<City>> getCities(@Query("page") String page);
}
