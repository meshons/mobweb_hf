package hu.dornyayse.liveresultat_viewer.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import hu.dornyayse.liveresultat_viewer.database.entities.ClassEntity;

@Dao
public interface ClassDao {

    @Query("SELECT * FROM class WHERE competition_id = :competitionId")
    List<ClassEntity> findAllOfCompetition(Long competitionId);

    @Query("SELECT * FROM class WHERE id = :id")
    ClassEntity findById(Long id);

    @Insert
    Long insert(ClassEntity classEntity);
}
