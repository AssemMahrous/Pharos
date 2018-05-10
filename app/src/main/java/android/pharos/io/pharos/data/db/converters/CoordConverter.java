package android.pharos.io.pharos.data.db.converters;

import android.arch.persistence.room.TypeConverter;
import android.pharos.io.pharos.data.network.models.Coord;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class CoordConverter {
    @TypeConverter
    public static Coord fromString(String value) {
        Gson gson = new Gson();
        Type listType = new TypeToken<Coord>() {
        }.getType();
        return gson.fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(Coord payloads) {
        Gson gson = new Gson();
        Type type = new TypeToken<Coord>() {
        }.getType();
        return gson.toJson(payloads, type);
    }
}
