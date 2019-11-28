package hu.dornyayse.liveresultat_viewer.data;

import androidx.room.TypeConverter;

import java.util.Date;

import hu.dornyayse.liveresultat_viewer.data.model.Status;

public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }


    @TypeConverter
    public static Status intToStatus(int value) {
        Status ret = null;
        for (Status status : Status.values()) {
            if (status.getValue() == value) {
                ret = status;
                break;
            }
        }
        return ret;
    }

    @TypeConverter
    public static int toInt(Status status) {
        return status.getValue();
    }
}
