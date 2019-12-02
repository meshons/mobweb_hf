package hu.dornyayse.liveresultat_viewer.service.database;

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
import hu.dornyayse.liveresultat_viewer.service.DataHolder;

public class DataMapper {
    private DataHolder dataHolder;

    public DataMapper(DataHolder _dataHolder) {
        dataHolder = _dataHolder;
    }

    public ClassEntity convert(Class classModel) {
        ClassEntity classEntity = new ClassEntity();
        classEntity.id = classModel.getId();
        classEntity.competitionId = classModel.getCompetition().getId();
        classEntity.className = classModel.getName();
        return classEntity;
    }

    public Class convert(ClassEntity classEntity) {
        Class classModel = new Class();
        classModel.setId(classEntity.id);
        classModel.setCompetition(dataHolder.getCompetition(classEntity.competitionId));
        classModel.setName(classEntity.className);
        classModel.setSplitControls(dataHolder.getSplitControlsOfClass(classEntity.id));
        classModel.setResults(dataHolder.getResultsOfClass(classEntity.id));
        return classModel;
    }

    public CompetitionEntity convert(Competition competitionModel) {
        CompetitionEntity competitionEntity = new CompetitionEntity();
        competitionEntity.id = competitionModel.getId();
        competitionEntity.apiId = competitionModel.getApiId();
        competitionEntity.name = competitionModel.getName();
        competitionEntity.organizer = competitionModel.getOrganizer();
        competitionEntity.date = competitionModel.getDate();
        competitionEntity.timeDiff = competitionModel.getTimeDiff();
        return competitionEntity;
    }

    public Competition convert(CompetitionEntity competitionEntity) {
        Competition competition = new Competition();
        competition.setId(competitionEntity.id);
        competition.setApiId(competitionEntity.apiId);
        competition.setName(competitionEntity.name);
        competition.setOrganizer(competitionEntity.organizer);
        competition.setDate(competitionEntity.date);
        competition.setTimeDiff(competitionEntity.timeDiff);
        competition.setClasses(dataHolder.getClassesOfCompetition(competitionEntity.id));
        competition.setHashes(dataHolder.getHashesOfCompetition(competitionEntity.id));
        competition.setLastPassings(dataHolder.getLastPassingsOfCompetition(competitionEntity.id));
        return competition;
    }

    public HashEntity convert(Hash hash) {
        HashEntity hashEntity = new HashEntity();
        hashEntity.id = hash.getId();
        hashEntity.competitionId = hash.getCompetition().getId();
        hashEntity.method = hash.getMethod();
        hashEntity.hash = hash.getHash();
        return hashEntity;
    }

    public Hash convert(HashEntity hashEntity) {
        Hash hash = new Hash();
        hash.setId(hashEntity.id);
        hash.setCompetition(dataHolder.getCompetition(hashEntity.competitionId));
        hash.setMethod(hashEntity.method);
        hash.setHash(hashEntity.hash);
        return hash;
    }

    public SplitControlEntity convert(SplitControl splitControl) {
        SplitControlEntity splitControlEntity = new SplitControlEntity();
        splitControlEntity.id = splitControl.getId();
        splitControlEntity.classId = splitControl.getOwnerClass().getId();
        splitControlEntity.code = splitControl.getCode();
        splitControlEntity.name = splitControl.getName();
        return splitControlEntity;
    }

    public SplitControl convert(SplitControlEntity splitControlEntity) {
        SplitControl splitControl = new SplitControl();
        splitControl.setId(splitControlEntity.id);
        splitControl.setOwnerClass(dataHolder.getClassById(splitControlEntity.classId));
        splitControl.setCode(splitControlEntity.code);
        splitControl.setName(splitControlEntity.name);
        return splitControl;
    }

    public LastPassingEntity convert(LastPassing lastPassing) {
        LastPassingEntity lastPassingEntity = new LastPassingEntity();
        lastPassingEntity.id = lastPassing.getId();
        lastPassingEntity.competitionId = lastPassing.getCompetition().getId();
        lastPassingEntity.passTime = lastPassing.getPassTime();
        lastPassingEntity.runnerName = lastPassing.getRunnerName();
        lastPassingEntity.className = lastPassing.getClassName();
        lastPassingEntity.control = lastPassing.getControl();
        lastPassingEntity.controlName = lastPassing.getControlName();
        lastPassingEntity.time = lastPassing.getTime();
        return lastPassingEntity;
    }

    public LastPassing convert(LastPassingEntity lastPassingEntity) {
        LastPassing lastPassing = new LastPassing();
        lastPassing.setId(lastPassingEntity.id);
        lastPassing.setCompetition(dataHolder.getCompetition(lastPassingEntity.competitionId));
        lastPassing.setPassTime(lastPassingEntity.passTime);
        lastPassing.setRunnerName(lastPassingEntity.runnerName);
        lastPassing.setClassName(lastPassingEntity.className);
        lastPassing.setControl(lastPassingEntity.control);
        lastPassing.setControlName(lastPassingEntity.controlName);
        lastPassing.setTime(lastPassingEntity.time);
        return lastPassing;
    }

    public ResultEntity convert(Result result) {
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.id = result.getId();
        resultEntity.classId = result.getOwnerClass().getId();
        resultEntity.club = result.getClub();
        resultEntity.name = result.getName();
        resultEntity.place = result.getPlace();
        resultEntity.progress = result.getProgress();
        resultEntity.start = result.getStart();
        resultEntity.timePlus = result.getTimePlus();
        resultEntity.result = result.getResult();
        resultEntity.status = result.getStatus();
        return resultEntity;
    }

    public Result convert(ResultEntity resultEntity) {
        Result result = new Result();
        result.setId(resultEntity.id);
        result.setOwnerClass(dataHolder.getClassById(resultEntity.classId));
        result.setClub(resultEntity.club);
        result.setName(resultEntity.name);
        result.setPlace(resultEntity.place);
        result.setProgress(resultEntity.progress);
        result.setStart(resultEntity.start);
        result.setTimePlus(resultEntity.timePlus);
        result.setResult(resultEntity.result);
        result.setStatus(resultEntity.status);
        result.setSplits(dataHolder.getSplitsOfResult(resultEntity.id));
        return result;
    }

    public SplitTimeEntity convert(SplitTime splitTime) {
        SplitTimeEntity splitTimeEntity = new SplitTimeEntity();
        splitTimeEntity.id = splitTime.getId();
        splitTimeEntity.resultId = splitTime.getResult().getId();
        splitTimeEntity.codeId = splitTime.getSplitControl().getId();
        splitTimeEntity.place = splitTime.getPlace();
        splitTimeEntity.timePlus = splitTime.getTimePlus();
        splitTimeEntity.status = splitTime.getStatus();
        return splitTimeEntity;
    }

    public SplitTime convert(SplitTimeEntity splitTimeEntity) {
        SplitTime splitTime = new SplitTime();
        splitTime.setId(splitTimeEntity.id);
        splitTime.setSplitControl(dataHolder.getSplitControl(splitTimeEntity.codeId));
        splitTime.setResult(dataHolder.getResult(splitTimeEntity.resultId));
        splitTime.setPlace(splitTimeEntity.place);
        splitTime.setTimePlus(splitTimeEntity.timePlus);
        splitTime.setStatus(splitTimeEntity.status);
        return splitTime;
    }
}
