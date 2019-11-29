package hu.dornyayse.liveresultat_viewer.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "class", foreignKeys = @ForeignKey(
        entity = CompetitionEntity.class,
        parentColumns = "id",
        childColumns = "competition_id"
), indices = {@Index("id"), @Index("competition_id")})
public class ClassEntity {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo(name = "competition_id")
    public Long competitionId;

    @ColumnInfo(name = "class_name")
    public String className;
}
