package hu.dornyayse.liveresultat_viewer.model;

import java.util.HashMap;

public class Class {

    private Long id;

    private Competition competition;

    private String name;

    private HashMap<Long, SplitControl> splitControls;

    private HashMap<Long, Result> results;
}
