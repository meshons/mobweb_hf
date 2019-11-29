package hu.dornyayse.liveresultat_viewer.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import hu.dornyayse.liveresultat_viewer.database.entities.ResultEntity;

@Dao
public interface ResultDao {

    @Query("SELECT * FROM result WHERE class_id = :classId")
    List<ResultEntity> findAllOfClass(Long classId);

    @Query("SELECT * FROM result WHERE id = :id")
    ResultEntity findById(Long id);

    @Insert
    Long insert(ResultEntity resultEntity);
}
