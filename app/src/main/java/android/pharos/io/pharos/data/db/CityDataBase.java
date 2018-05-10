package android.pharos.io.pharos.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.pharos.io.pharos.data.db.converters.CityConverter;
import android.pharos.io.pharos.data.db.converters.CoordConverter;

@Database(entities = {CityEntry.class}, version = 1)
@TypeConverters({CityConverter.class, CoordConverter.class})

public abstract class CityDataBase extends RoomDatabase {

    private static final String DATABASE_NAME = "citiesDB";

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static volatile CityDataBase sInstance;

    public static CityDataBase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            CityDataBase.class, CityDataBase.DATABASE_NAME)
                            .fallbackToDestructiveMigration().build();
                }
            }
        }
        return sInstance;
    }

    public abstract CityDao cityDao();
}
