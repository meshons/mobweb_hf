package hu.dornyayse.liveresultat_viewer.model;

import java.util.Date;
import java.util.HashMap;

public class Competition {

    private Long id;

    private Long apiId;

    private String Name;

    private String Organizer;

    private Date date;

    private int timeDiff;

    private int multiDayStage;

    private Competition multiDayFirstDay;

    private HashMap<Long, Class> classes;

    private HashMap<Long, Hash> hashes;

    private HashMap<Long, LastPassing> lastPassings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApiId() {
        return apiId;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
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

    public Competition getMultiDayFirstDay() {
        return multiDayFirstDay;
    }

    public void setMultiDayFirstDay(Competition multiDayFirstDay) {
        this.multiDayFirstDay = multiDayFirstDay;
    }

    public HashMap<Long, Class> getClasses() {
        return classes;
    }

    public void setClasses(HashMap<Long, Class> classes) {
        this.classes = classes;
    }

    public HashMap<Long, Hash> getHashes() {
        return hashes;
    }

    public void setHashes(HashMap<Long, Hash> hashes) {
        this.hashes = hashes;
    }

    public HashMap<Long, LastPassing> getLastPassings() {
        return lastPassings;
    }

    public void setLastPassings(HashMap<Long, LastPassing> lastPassings) {
        this.lastPassings = lastPassings;
    }
}
