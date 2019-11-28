package hu.dornyayse.liveresultat_viewer.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import hu.dornyayse.liveresultat_viewer.data.model.Status;

@Entity(tableName = "results", foreignKeys = @ForeignKey(
        entity = Class.class,
        parentColumns = "id",
        childColumns = "class_id"
))
public class Result {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo(name = "class_id")
    public Long classId;

    @ColumnInfo(name = "club")
    public String club;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "place")
    public Integer place;

    @ColumnInfo(name = "progress")
    public Integer progess;

    @ColumnInfo(name = "start")
    public Long start;

    @ColumnInfo(name = "time_plus")
    public Long timePlus;

    @ColumnInfo(name = "result")
    public Long result;

    @ColumnInfo(name = "status")
    public Status status;
}
