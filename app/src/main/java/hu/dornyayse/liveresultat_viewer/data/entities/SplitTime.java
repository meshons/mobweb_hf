package hu.dornyayse.liveresultat_viewer.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import hu.dornyayse.liveresultat_viewer.data.model.Status;

@Entity(tableName = "split_times", foreignKeys = {@ForeignKey(
        entity = Result.class,
        parentColumns = "id",
        childColumns = "result_id"
),  @ForeignKey(
        entity = SplitControls.class,
        parentColumns = "id",
        childColumns = "code_id"
)})
public class SplitTime {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo(name = "result_id")
    public Long resultId;

    @ColumnInfo(name = "code_id")
    public Long codeId;

    @ColumnInfo(name = "place")
    public Integer place;

    @ColumnInfo(name = "time_plus")
    public Long timePlus;

    @ColumnInfo(name = "status")
    public Status status;
}
