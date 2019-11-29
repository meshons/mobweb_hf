package hu.dornyayse.liveresultat_viewer.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "split_control", foreignKeys = @ForeignKey(
        entity = ClassEntity.class,
        parentColumns = "id",
        childColumns = "class_id"
), indices = {@Index("id"), @Index("class_id")})
public class SplitControlEntity {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo(name = "class_id")
    public Long classId;

    @ColumnInfo(name = "code")
    public Integer code;

    @ColumnInfo(name = "name")
    public String name;
}
