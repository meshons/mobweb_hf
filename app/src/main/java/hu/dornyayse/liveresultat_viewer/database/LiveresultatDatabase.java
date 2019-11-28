package hu.dornyayse.liveresultat_viewer.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import hu.dornyayse.liveresultat_viewer.database.dao.ClassDao;
import hu.dornyayse.liveresultat_viewer.database.dao.CompetitionDao;
import hu.dornyayse.liveresultat_viewer.database.dao.HashDao;
import hu.dornyayse.liveresultat_viewer.database.dao.LastPassingDao;
import hu.dornyayse.liveresultat_viewer.database.dao.ResultDao;
import hu.dornyayse.liveresultat_viewer.database.dao.SplitControlsDao;
import hu.dornyayse.liveresultat_viewer.database.dao.SplitTimeDao;
import hu.dornyayse.liveresultat_viewer.database.entities.ClassEntity;
import hu.dornyayse.liveresultat_viewer.database.entities.CompetitionEntity;
import hu.dornyayse.liveresultat_viewer.database.entities.HashEntity;
import hu.dornyayse.liveresultat_viewer.database.entities.LastPassingEntity;
import hu.dornyayse.liveresultat_viewer.database.entities.ResultEntity;
import hu.dornyayse.liveresultat_viewer.database.entities.SplitControlsEntity;
import hu.dornyayse.liveresultat_viewer.database.entities.SplitTimeEntity;

@Database(
        entities = {
                ClassEntity.class,
                CompetitionEntity.class,
                HashEntity.class,
                LastPassingEntity.class,
                ResultEntity.class,
                SplitControlsEntity.class,
                SplitTimeEntity.class
        },
        version = 1
)
@TypeConverters(value = {Converters.class})
public abstract class LiveresultatDatabase extends RoomDatabase {

        public abstract ClassDao classDao();

        public abstract CompetitionDao competitionDao();

        public abstract HashDao hashDao();

        public abstract LastPassingDao lastPassingDao();

        public abstract ResultDao resultDao();

        public abstract SplitControlsDao splitControlsDao();

        public abstract SplitTimeDao splitTimeDao();
}
