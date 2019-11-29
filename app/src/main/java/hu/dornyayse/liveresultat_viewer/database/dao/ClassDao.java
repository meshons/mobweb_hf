package hu.dornyayse.liveresultat_viewer.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import hu.dornyayse.liveresultat_viewer.database.entities.ClassEntity;

@Dao
public interface ClassDao {

    @Query("SELECT * FROM class WHERE competition_id = :competitionId")
    List<ClassEntity> findAllOfCompetition(Long competitionId);

    @Query("SELECT * FROM result WHERE id = :id")
    ClassEntity findById(Long id);

    @Insert
    Long insert(ClassEntity classEntity);

    @Update
    void update(ClassEntity classEntity);

    @Delete
    void delete(ClassEntity classEntity);
}
