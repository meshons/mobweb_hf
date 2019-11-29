package hu.dornyayse.liveresultat_viewer.model;

public class SplitTime {

    private Long id;

    private SplitControl splitControl;

    private Result result;

    private Integer place;

    private Long timePlus;

    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SplitControl getSplitControl() {
        return splitControl;
    }

    public void setSplitControl(SplitControl splitControl) {
        this.splitControl = splitControl;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Long getTimePlus() {
        return timePlus;
    }

    public void setTimePlus(Long timePlus) {
        this.timePlus = timePlus;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
