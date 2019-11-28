package hu.dornyayse.liveresultat_viewer.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import hu.dornyayse.liveresultat_viewer.database.entities.CompetitionEntity;

@Dao
public interface ResultDao {

    @Query("SELECT * FROM result")
    List<CompetitionEntity> findAll();

    @Insert
    Long insert(CompetitionEntity competitionEntity);

    @Update
    void update(CompetitionEntity competitionEntity);

    @Delete
    void delete(CompetitionEntity competitionEntity);
}
