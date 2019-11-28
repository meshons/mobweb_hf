package hu.dornyayse.liveresultat_viewer.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import hu.dornyayse.liveresultat_viewer.database.entities.HashEntity;

@Dao
public interface HashDao {

    @Query("SELECT * FROM hash")
    List<HashEntity> findAll();

    @Insert
    Long insert(HashEntity hashEntity);

    @Update
    void update(HashEntity hashEntity);
}
