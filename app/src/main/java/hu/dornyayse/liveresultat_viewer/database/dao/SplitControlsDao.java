package hu.dornyayse.liveresultat_viewer.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import hu.dornyayse.liveresultat_viewer.database.entities.SplitControlEntity;

@Dao
public interface SplitControlsDao {

    @Query("SELECT * FROM split_control")
    List<SplitControlEntity> findAll();

    @Insert
    Long insert(SplitControlEntity splitControlEntity);

    @Update
    void update(SplitControlEntity splitControlEntity);

    @Delete
    void delete(SplitControlEntity splitControlEntity);
}
