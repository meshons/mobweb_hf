package hu.dornyayse.liveresultat_viewer.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import hu.dornyayse.liveresultat_viewer.database.entities.CompetitionEntity;

@Dao
public interface CompetitionDao {

    @Query("SELECT * FROM competition")
    List<CompetitionEntity> findAll();

    @Query("SELECT * FROM competition WHERE id = :id")
    CompetitionEntity findById(Long id);

    @Insert
    Long insert(CompetitionEntity competitionEntity);

    @Update
    void update(CompetitionEntity competitionEntity);

    @Delete
    void delete(CompetitionEntity competitionEntity);
}
