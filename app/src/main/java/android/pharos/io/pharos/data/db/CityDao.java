package android.pharos.io.pharos.data.db;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

public interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(CityEntry... cities);
}
