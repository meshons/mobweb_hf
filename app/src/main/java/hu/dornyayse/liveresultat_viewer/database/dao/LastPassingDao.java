package hu.dornyayse.liveresultat_viewer.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import hu.dornyayse.liveresultat_viewer.database.entities.LastPassingEntity;

@Dao
public interface LastPassingDao {

    @Query("SELECT * FROM last_passing WHERE competition_id = :competitionId")
    List<LastPassingEntity> findAllOfCompetition(Long competitionId);

    @Insert
    Long insert(LastPassingEntity lastPassingEntity);
}
