package hu.dornyayse.liveresultat_viewer.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import hu.dornyayse.liveresultat_viewer.database.entities.ResultEntity;

@Dao
public interface ResultDao {

    @Query("SELECT * FROM result WHERE class_id = :classId")
    List<ResultEntity> findAllOfClass(Long classId);

    @Query("SELECT * FROM class WHERE id = :id")
    ResultEntity findById(Long id);

    @Insert
    Long insert(ResultEntity resultEntity);

    @Update
    void update(ResultEntity resultEntity);

    @Delete
    void delete(ResultEntity resultEntity);
}
