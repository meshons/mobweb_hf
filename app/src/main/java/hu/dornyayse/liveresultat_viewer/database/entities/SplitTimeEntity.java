package hu.dornyayse.liveresultat_viewer.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import hu.dornyayse.liveresultat_viewer.model.Status;

@Entity(tableName = "split_time", foreignKeys = {@ForeignKey(
        entity = ResultEntity.class,
        parentColumns = "id",
        childColumns = "result_id"
),  @ForeignKey(
        entity = SplitControlEntity.class,
        parentColumns = "id",
        childColumns = "code_id"
)}, indices = {@Index("id"), @Index("result_id"), @Index("code_id")})
public class SplitTimeEntity {
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
