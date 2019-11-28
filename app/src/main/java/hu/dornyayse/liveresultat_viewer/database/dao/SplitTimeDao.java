package hu.dornyayse.liveresultat_viewer.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import hu.dornyayse.liveresultat_viewer.database.entities.SplitTimeEntity;

@Dao
public interface SplitTimeDao {

    @Query("SELECT * FROM split_time")
    List<SplitTimeEntity> findAll();

    @Insert
    Long insert(SplitTimeEntity splitTimeEntity);

    @Update
    void update(SplitTimeEntity splitTimeEntity);

    @Delete
    void delete(SplitTimeEntity splitTimeEntity);
}