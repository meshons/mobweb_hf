package hu.dornyayse.liveresultat_viewer.service;

import androidx.room.Room;

import java.util.HashMap;
import java.util.List;

import hu.dornyayse.liveresultat_viewer.database.LiveresultatDatabase;
import hu.dornyayse.liveresultat_viewer.database.entities.ClassEntity;
import hu.dornyayse.liveresultat_viewer.database.entities.CompetitionEntity;
import hu.dornyayse.liveresultat_viewer.database.entities.HashEntity;
import hu.dornyayse.liveresultat_viewer.database.entities.LastPassingEntity;
import hu.dornyayse.liveresultat_viewer.database.entities.ResultEntity;
import hu.dornyayse.liveresultat_viewer.database.entities.SplitControlEntity;
import hu.dornyayse.liveresultat_viewer.database.entities.SplitTimeEntity;
import hu.dornyayse.liveresultat_viewer.model.Class;
import hu.dornyayse.liveresultat_viewer.model.Competition;
import hu.dornyayse.liveresultat_viewer.model.Hash;
import hu.dornyayse.liveresultat_viewer.model.LastPassing;
import hu.dornyayse.liveresultat_viewer.model.Result;
import hu.dornyayse.liveresultat_viewer.model.SplitControl;
import hu.dornyayse.liveresultat_viewer.model.SplitTime;

public class DataHolder {

    private LiveresultatDatabase liveresultatDatabase = ServiceLocator
            .getInstance().getLiveresultatDatabase();
    private hu.dornyayse.liveresultat_viewer.service.database.DataMapper databaseDataMapper;
    private hu.dornyayse.liveresultat_viewer.service.network.DataMapper networkDataMapper;

    private HashMap<Long, Competition> competitions = new HashMap<>();
    private HashMap<Long, Competition> competitionsByApiId = new HashMap<>();
    private HashMap<Long, Class> classes = new HashMap<>();
    private HashMap<Long, Hash> hashes = new HashMap<>();
    private HashMap<Long, Result> results = new HashMap<>();
    private HashMap<Long, LastPassing> lastPassings = new HashMap<>();
    private HashMap<Long, SplitControl> splitControls = new HashMap<>();
    private HashMap<Long, SplitTime> splitTimes = new HashMap<>();

    public DataHolder() {
        connectToDatabase();
        liveresultatDatabase.beginTransaction();
        competitions = loadCompetitions();
        liveresultatDatabase.endTransaction();
        closeConnectionToDatabase();
    }

    public void save() {
        connectToDatabase();
        liveresultatDatabase.beginTransaction();
        liveresultatDatabase.clearAllTables();
        for (Competition competition : competitions.values())
            competition.setId(
                    liveresultatDatabase.competitionDao()
                            .insert(databaseDataMapper.convert(competition))
            );
        for (Class classModel : classes.values())
            classModel.setId(
                    liveresultatDatabase.classDao()
                            .insert(databaseDataMapper.convert(classModel))
            );
        for (Hash hash : hashes.values())
            hash.setId(
                    liveresultatDatabase.hashDao()
                            .insert(databaseDataMapper.convert(hash))
            );
        for (Result result : results.values())
            result.setId(
                    liveresultatDatabase.resultDao()
                            .insert(databaseDataMapper.convert(result))
            );
        for (LastPassing lastPassing : lastPassings.values())
            lastPassing.setId(
                    liveresultatDatabase.lastPassingDao()
                            .insert(databaseDataMapper.convert(lastPassing))
            );
        for (SplitControl splitControl : splitControls.values())
            splitControl.setId(
                    liveresultatDatabase.splitControlsDao()
                            .insert(databaseDataMapper.convert(splitControl))
            );
        for (SplitTime splitTime : splitTimes.values())
            splitTime.setId(
                    liveresultatDatabase.splitTimeDao()
                            .insert(databaseDataMapper.convert(splitTime))
            );
        liveresultatDatabase.endTransaction();
        closeConnectionToDatabase();
    }

    private void connectToDatabase() {
        liveresultatDatabase = Room.databaseBuilder(
                ServiceLocator.getInstance().getApplicationContext(),
                LiveresultatDatabase.class,
                "liveresultat"
        ).build();
    }

    private void closeConnectionToDatabase() {
        if (liveresultatDatabase != null && liveresultatDatabase.isOpen())
            liveresultatDatabase.close();
        liveresultatDatabase = null;
    }

    private HashMap<Long, Competition> loadCompetitions() {
        HashMap<Long, Competition> competitions = new HashMap<>();
        List<CompetitionEntity> competitionEntities = liveresultatDatabase
                .competitionDao().findAll();

        for (CompetitionEntity competitionEntity : competitionEntities) {
            Competition competition = databaseDataMapper.convert(competitionEntity);
            competitions.put(competition.getId(), competition);
            competitionsByApiId.put(competition.getApiId(), competition);
        }

        return competitions;
    }

    public Competition getCompetition(Long id) {
        if (competitions.containsKey(id))
            return competitions.get(id);
        Competition competition = databaseDataMapper.convert(
                liveresultatDatabase.competitionDao().findById(id)
        );
        competitions.put(id, competition);
        return competition;
    }

    public Class getClassById(Long id) {
        if (classes.containsKey(id))
            return classes.get(id);
        Class classModel = databaseDataMapper.convert(
                liveresultatDatabase.classDao().findById(id)
        );
        classes.put(id, classModel);
        return classModel;
    }

    public Result getResult(Long id) {
        if (results.containsKey(id))
            return results.get(id);
        Result result = databaseDataMapper.convert(
                liveresultatDatabase.resultDao().findById(id)
        );
        results.put(id, result);
        return result;
    }

    public SplitControl getSplitControl(Long id) {
        if (splitControls.containsKey(id))
            return splitControls.get(id);
        SplitControl splitControl = databaseDataMapper.convert(
                liveresultatDatabase.splitControlsDao().findById(id)
        );
        splitControls.put(id, splitControl);
        return splitControl;
    }

    public HashMap<Long, Class> getClassesOfCompetition(Long id) {
        HashMap<Long, Class> classesOfCompetition = new HashMap<>();
        List<ClassEntity> classEntities = liveresultatDatabase.classDao().findAllOfCompetition(id);
        for (ClassEntity classEntity : classEntities) {
            Class classModel = databaseDataMapper.convert(classEntity);
            classesOfCompetition.put(classModel.getId(), classModel);
        }
        classes.putAll(classesOfCompetition);
        return classesOfCompetition;
    }

    public HashMap<Long, Hash> getHashesOfCompetition(Long id) {
        HashMap<Long, Hash> hashesOfCompetition = new HashMap<>();
        List<HashEntity> hashEntities = liveresultatDatabase.hashDao().findAllOfCompetition(id);
        for (HashEntity hashEntity : hashEntities) {
            Hash hash = databaseDataMapper.convert(hashEntity);
            hashesOfCompetition.put(hash.getId(), hash);
        }
        hashes.putAll(hashesOfCompetition);
        return hashesOfCompetition;
    }

    public HashMap<Long, SplitControl> getSplitControlsOfClass(Long id) {
        HashMap<Long, SplitControl> splitControlsOfClass = new HashMap<>();
        List<SplitControlEntity> splitControlEntities = liveresultatDatabase
                .splitControlsDao().findAllOfClass(id);
        for (SplitControlEntity splitControlEntity : splitControlEntities) {
            SplitControl splitControl = databaseDataMapper.convert(splitControlEntity);
            splitControlsOfClass.put(splitControl.getId(), splitControl);
        }
        splitControls.putAll(splitControlsOfClass);
        return splitControlsOfClass;
    }

    public HashMap<Long, Result> getResultsOfClass(Long id) {
        HashMap<Long, Result> resultsOfClass = new HashMap<>();
        List<ResultEntity> resultEntities = liveresultatDatabase
                .resultDao().findAllOfClass(id);
        for (ResultEntity resultEntity : resultEntities) {
            Result result = databaseDataMapper.convert(resultEntity);
            resultsOfClass.put(result.getId(), result);
        }
        results.putAll(resultsOfClass);
        return resultsOfClass;
    }

    public HashMap<Long, LastPassing> getLastPassingsOfCompetition(Long id) {
        HashMap<Long, LastPassing> lastpassingsOfCompetition = new HashMap<>();
        List<LastPassingEntity> lastPassingEntities = liveresultatDatabase
                .lastPassingDao().findAllOfCompetition(id);
        for (LastPassingEntity lastPassingEntity : lastPassingEntities) {
            LastPassing lastPassing = databaseDataMapper.convert(lastPassingEntity);
            lastpassingsOfCompetition.put(lastPassing.getId(), lastPassing);
        }
        lastPassings.putAll(lastpassingsOfCompetition);
        return lastpassingsOfCompetition;
    }

    public HashMap<Long, SplitTime> getSplitsOfResult(Long id) {
        HashMap<Long, SplitTime> splitsOfResult = new HashMap<>();
        List <SplitTimeEntity> splitTimeEntities = liveresultatDatabase
                .splitTimeDao().findAllOfResult(id);
        for (SplitTimeEntity splitTimeEntity : splitTimeEntities) {
            SplitTime splitTime = databaseDataMapper.convert(splitTimeEntity);
            splitsOfResult.put(splitTime.getId(), splitTime);
        }
        splitTimes.putAll(splitsOfResult);
        return splitsOfResult;
    }

    public HashMap<Long, Competition> getCompetitions() {
        return competitions;
    }

    public HashMap<Long, Competition> getCompetitionsByApiId() {
        return competitionsByApiId;
    }

    public HashMap<Long, Class> getClasses() {
        return classes;
    }

    public HashMap<Long, Hash> getHashes() {
        return hashes;
    }

    public HashMap<Long, Result> getResults() {
        return results;
    }

    public HashMap<Long, LastPassing> getLastPassings() {
        return lastPassings;
    }

    public HashMap<Long, SplitControl> getSplitControls() {
        return splitControls;
    }

    public HashMap<Long, SplitTime> getSplitTimes() {
        return splitTimes;
    }
}
