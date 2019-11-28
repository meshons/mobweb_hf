package hu.dornyayse.liveresultat_viewer.model;

import java.util.HashMap;

class Result {

    private Long id;

    private Class ownerClass;

    private String club;

    private String name;

    private Integer place;

    private Integer progress;

    private Long start;

    private Long timePlus;

    private Long result;

    private Status status;

    private HashMap<Long, SplitTime> splits;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Class getOwnerClass() {
        return ownerClass;
    }

    public void setOwnerClass(Class ownerClass) {
        this.ownerClass = ownerClass;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getTimePlus() {
        return timePlus;
    }

    public void setTimePlus(Long timePlus) {
        this.timePlus = timePlus;
    }

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public HashMap<Long, SplitTime> getSplits() {
        return splits;
    }

    public void setSplits(HashMap<Long, SplitTime> splits) {
        this.splits = splits;
    }
}
