package hu.dornyayse.liveresultat_viewer.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "last_passing", foreignKeys = @ForeignKey(
        entity = CompetitionEntity.class,
        parentColumns = "id",
        childColumns = "competition_id"
), indices = {@Index("id"), @Index("competition_id")})
public class LastPassingEntity {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo(name = "competition_id")
    public Long competitionId;

    @ColumnInfo(name = "pass_time")
    public String passTime;

    @ColumnInfo(name = "runner_name")
    public String runnerName;

    @ColumnInfo(name = "class")
    public String className;

    @ColumnInfo(name = "control")
    public Integer control;

    @ColumnInfo(name = "control_name")
    public String controlName;

    @ColumnInfo(name = "time")
    public Long time;
}
