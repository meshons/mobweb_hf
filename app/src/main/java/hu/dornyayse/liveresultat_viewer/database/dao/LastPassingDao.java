package hu.dornyayse.liveresultat_viewer.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import hu.dornyayse.liveresultat_viewer.database.entities.LastPassingEntity;

@Dao
public interface LastPassingDao {

    @Query("SELECT * FROM last_passing")
    List<LastPassingEntity> findAll();

    @Insert
    Long insert(LastPassingEntity lastPassingEntity);

    @Update
    void update(LastPassingEntity lastPassingEntity);

    @Delete
    void delete(LastPassingEntity lastPassingEntity);
}
