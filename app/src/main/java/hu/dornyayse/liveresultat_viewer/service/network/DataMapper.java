package hu.dornyayse.liveresultat_viewer.service.network;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import hu.dornyayse.liveresultat_viewer.model.Competition;
import hu.dornyayse.liveresultat_viewer.network.model.CompetitionData;
import hu.dornyayse.liveresultat_viewer.service.DataHolder;

public class DataMapper {

    private DataHolder dataHolder;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    public DataMapper(DataHolder _dataHolder) {
        dataHolder = _dataHolder;
    }

    public Competition convert(CompetitionData competitionData) {
        Competition competition = new Competition();
        competition.setApiId(competitionData.id);
        competition.setName(competitionData.name);
        competition.setOrganizer(competitionData.organizer);
        Date d = null;
        try {
            d = dateFormat.parse(competitionData.date);
        } catch (ParseException e) {
            Log.d("api", "time format not correct");
            d = new Date();
        }
        competition.setDate(d);
        competition.setTimeDiff(competitionData.timediff);
        return competition;
    }

    public boolean modified(CompetitionData competitionData, Competition competition) {
        boolean modified = false;
        if (!competitionData.name.equals(competition.getName())) {
            competition.setName(competitionData.name);
            modified = true;
        }
        if (!competitionData.organizer.equals(competition.getOrganizer())) {
            competition.setOrganizer(competitionData.organizer);
            modified = true;
        }
        Date d = null;
        try {
             d = dateFormat.parse(competitionData.date);
             if (d != null && !d.equals(competition.getDate())) {
                 competition.setDate(d);
                 modified = true;
             }
        } catch (ParseException e) {
            Log.d("api", "time format not correct");
            d = new Date();
        }
        if (competitionData.timediff != competition.getTimeDiff()) {
            competition.setTimeDiff(competitionData.timediff);
            modified = true;
        }
        return modified;
    }

    public void patch(Competition competition, CompetitionData competitionData) {
        competition.setName(competitionData.name);
        competition.setOrganizer(competitionData.organizer);
        Date d = null;
        try {
            d = dateFormat.parse(competitionData.date);
        } catch (ParseException e) {
            Log.d("api", "time format not correct");
            d = new Date();
        }
        competition.setDate(d);
        competition.setTimeDiff(competitionData.timediff);
    }
}
