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
}
