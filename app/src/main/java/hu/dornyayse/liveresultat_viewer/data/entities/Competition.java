package hu.dornyayse.liveresultat_viewer.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "competitions")
public class Competition {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "organizer")
    public String organizer;

    @ColumnInfo(name = "date")
    public Date date;

    @ColumnInfo(name = "time_diff")
    public int timeDiff;

    @ColumnInfo(name = "multi_day_stage")
    public int multiDayStage;

    @ColumnInfo(name = "multi_day_first_stage")
    public Long multiDayFirstStage;
}
