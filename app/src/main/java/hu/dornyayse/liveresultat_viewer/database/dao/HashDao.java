package hu.dornyayse.liveresultat_viewer.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import hu.dornyayse.liveresultat_viewer.database.entities.HashEntity;

@Dao
public interface HashDao {

    @Query("SELECT * FROM hash WHERE competition_id = :competitionId")
    List<HashEntity> findAllOfCompetition(Long competitionId);

    @Insert
    Long insert(HashEntity hashEntity);
}
