package hu.dornyayse.liveresultat_viewer.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import hu.dornyayse.liveresultat_viewer.database.entities.SplitTimeEntity;

@Dao
public interface SplitTimeDao {

    @Query("SELECT * FROM split_time WHERE result_id = :resultId")
    List<SplitTimeEntity> findAllOfResult(Long resultId);

    @Insert
    Long insert(SplitTimeEntity splitTimeEntity);
}
