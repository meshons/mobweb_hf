package hu.dornyayse.liveresultat_viewer.model;

import java.util.Date;
import java.util.HashMap;

public class Competition {

    private Long id;

    private String Name;

    private String Organizer;

    private Date date;

    private int timeDiff;

    private int multiDayStage;

    private Competition multiDayFirstStage;

    private HashMap<Long, Class> classes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getOrganizer() {
        return Organizer;
    }

    public void setOrganizer(String organizer) {
        Organizer = organizer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTimeDiff() {
        return timeDiff;
    }

    public void setTimeDiff(int timeDiff) {
        this.timeDiff = timeDiff;
    }

    public int getMultiDayStage() {
        return multiDayStage;
    }

    public void setMultiDayStage(int multiDayStage) {
        this.multiDayStage = multiDayStage;
    }

    public Competition getMultiDayFirstStage() {
        return multiDayFirstStage;
    }

    public void setMultiDayFirstStage(Competition multiDayFirstStage) {
        this.multiDayFirstStage = multiDayFirstStage;
    }

    public HashMap<Long, Class> getClasses() {
        return classes;
    }

    public void setClasses(HashMap<Long, Class> classes) {
        this.classes = classes;
    }
}
