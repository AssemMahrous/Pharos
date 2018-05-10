package android.pharos.io.pharos.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
@Dao
public interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(CityEntry... cities);
}
