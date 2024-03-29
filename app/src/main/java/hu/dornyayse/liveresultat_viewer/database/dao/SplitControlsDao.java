package hu.dornyayse.liveresultat_viewer.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import hu.dornyayse.liveresultat_viewer.database.entities.SplitControlEntity;

@Dao
public interface SplitControlsDao {

    @Query("SELECT * FROM split_control WHERE class_id = :classId")
    List<SplitControlEntity> findAllOfClass(Long classId);

    @Query("SELECT * FROM split_control WHERE id = :id")
    SplitControlEntity findById(Long id);

    @Insert
    Long insert(SplitControlEntity splitControlEntity);
}
