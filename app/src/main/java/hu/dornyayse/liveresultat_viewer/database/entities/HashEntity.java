package hu.dornyayse.liveresultat_viewer.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import hu.dornyayse.liveresultat_viewer.model.Method;

@Entity(tableName = "hashes", foreignKeys = @ForeignKey(
        entity = CompetitionEntity.class,
        parentColumns = "id",
        childColumns = "competition_id"
))public class HashEntity {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo(name = "competition_id")
    public Long competitionId;

    @ColumnInfo(name = "method")
    public Method method;

    @ColumnInfo(name = "hash")
    public String hash;
}
