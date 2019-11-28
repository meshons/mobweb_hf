package hu.dornyayse.liveresultat_viewer.model;

import java.util.HashMap;

public class Class {

    private Long id;

    private Competition competition;

    private String name;

    private HashMap<Long, SplitControl> splitControls;

    private HashMap<Long, Result> results;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Long, SplitControl> getSplitControls() {
        return splitControls;
    }

    public void setSplitControls(HashMap<Long, SplitControl> splitControls) {
        this.splitControls = splitControls;
    }

    public HashMap<Long, Result> getResults() {
        return results;
    }

    public void setResults(HashMap<Long, Result> results) {
        this.results = results;
    }
}
