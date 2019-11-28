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
}
