package hu.dornyayse.liveresultat_viewer.network.model;

import com.google.gson.annotations.SerializedName;

public class LastPassingData {

    public String passtime;

    public String runnerName;

    @SerializedName("class")
    public String className;

    public Integer control;

    public String controlName;

    public Long time;
}
