package hu.dornyayse.liveresultat_viewer.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "classes", foreignKeys = @ForeignKey(
        entity = Competition.class,
        parentColumns = "id",
        childColumns = "competition_id"
))
public class Class {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo(name = "competition_id")
    public Long competitionId;

    @ColumnInfo(name = "class_name")
    public String className;
}
