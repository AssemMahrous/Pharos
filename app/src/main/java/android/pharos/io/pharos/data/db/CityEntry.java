package android.pharos.io.pharos.data.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.pharos.io.pharos.data.db.converters.CityConverter;
import android.pharos.io.pharos.data.network.models.City;

@Entity(tableName = "city")
public class CityEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @TypeConverters(CityConverter.class)
    private City city;

    @TypeConverters(CityConverter.class)
    public City getCity() {
        return city;
    }

    public CityEntry(int id, City city) {
        this.id = id;
        this.city = city;
    }

    @Ignore
    public CityEntry(City city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }
}
