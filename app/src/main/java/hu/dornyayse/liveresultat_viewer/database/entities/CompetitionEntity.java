package hu.dornyayse.liveresultat_viewer.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "competition", indices = {@Index("id"), @Index("api_id")})
public class CompetitionEntity {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo(name = "api_id")
    public Long apiId;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "organizer")
    public String organizer;

    @ColumnInfo(name = "date")
    public Date date;

    @ColumnInfo(name = "time_diff")
    public int timeDiff;
}
