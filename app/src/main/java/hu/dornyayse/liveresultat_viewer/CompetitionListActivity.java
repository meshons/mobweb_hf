package hu.dornyayse.liveresultat_viewer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import hu.dornyayse.liveresultat_viewer.adapter.CompetitionAdapter;
import hu.dornyayse.liveresultat_viewer.model.Competition;
import hu.dornyayse.liveresultat_viewer.network.ApiManager;
import hu.dornyayse.liveresultat_viewer.network.model.CompetitionsData;
import hu.dornyayse.liveresultat_viewer.service.DataHolder;
import hu.dornyayse.liveresultat_viewer.service.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompetitionListActivity extends AppCompatActivity {

    private DataHolder dataHolder = ServiceLocator.getInstance().getDataHolder();
    private ApiManager apiManager = ServiceLocator.getInstance().getApiManager();

    private HashMap<Long, Competition> competitions;

    private RecyclerView competitionListView;
    private CompetitionAdapter competitionAdapter;

    private RecyclerView todayCompetitionListView;
    private CompetitionAdapter todayCompetitionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_list);

        competitionListView = findViewById(R.id.competition_list);
        competitionAdapter = new CompetitionAdapter();
        competitionListView.setLayoutManager(new LinearLayoutManager(this));
        competitionListView.setAdapter(competitionAdapter);
        todayCompetitionListView = findViewById(R.id.today_competition_list);
        todayCompetitionAdapter = new CompetitionAdapter();
        todayCompetitionListView.setLayoutManager(new LinearLayoutManager(this));
        todayCompetitionListView.setAdapter(todayCompetitionAdapter);

        new LoadData(this).execute();
    }

    public void mergeData(
            MergeDataObject mergeDataObject
    ) {
        new MergeData(this).execute(mergeDataObject);
    }

    private static class LoadData extends AsyncTask<Void, Void, HashMap<Long, Competition>> {

        private WeakReference<CompetitionListActivity> activityReference;

        LoadData(CompetitionListActivity activity) {
            this.activityReference = new WeakReference<CompetitionListActivity>(activity);
        }

        @Override
        protected HashMap<Long, Competition> doInBackground(Void... voids) {
            CompetitionListActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return null;

            activity.dataHolder.load(activity.getApplicationContext());
            return activity.dataHolder.getCompetitions();
        }

        @Override
        protected void onPostExecute(HashMap<Long, Competition> loadedCompetitions) {
            CompetitionListActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;

            activity.competitions = loadedCompetitions;

            List<Competition> orderedCompetition = new ArrayList<>(activity.competitions.values());
            Collections.sort(orderedCompetition);
            activity.competitionAdapter.update(orderedCompetition);
            List<Competition> todayCompetitions = new ArrayList<>();
            for (Competition competition : activity.competitions.values()) {
                if (DateUtils.isToday(competition.getDate().getTime()))
                    todayCompetitions.add(competition);
            }
            Collections.sort(todayCompetitions);
            activity.todayCompetitionAdapter.update(todayCompetitions);
            activity.loadFromApi();
        }
    }

    private static class MergeData
            extends AsyncTask<MergeDataObject, Void, HashMap<Long, Competition>> {

        private WeakReference<CompetitionListActivity> activityReference;

        MergeData(CompetitionListActivity activity) {
            this.activityReference = new WeakReference<CompetitionListActivity>(activity);
        }

        @Override
        protected HashMap<Long, Competition> doInBackground(MergeDataObject... mergeDataObjects) {
            CompetitionListActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return null;

            MergeDataObject mergeDataObject = mergeDataObjects[0];

            activity.dataHolder.connectToDatabase(activity.getApplicationContext());
            activity.dataHolder.insertCompetitions(mergeDataObject.insertedCompetitions);
            activity.dataHolder.updateCompetitions(mergeDataObject.updatedCompetitions);
            activity.dataHolder.deleteCompetitions(mergeDataObject.deletedCompetitions);
            activity.dataHolder.closeConnectionToDatabase();

            return activity.competitions;
        }

        @Override
        protected void onPostExecute(HashMap<Long, Competition> loadedCompetitions) {
            CompetitionListActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;

            List<Competition> orderedCompetition = new ArrayList<>(activity.competitions.values());
            Collections.sort(orderedCompetition);
            activity.competitionAdapter.update(orderedCompetition);
            List<Competition> todayCompetitions = new ArrayList<>();
            for (Competition competition : activity.competitions.values()) {
                if (DateUtils.isToday(competition.getDate().getTime()))
                    todayCompetitions.add(competition);
            }
            Collections.sort(todayCompetitions);
            activity.todayCompetitionAdapter.update(todayCompetitions);
        }
    }

    private void loadFromApi() {
        apiManager.getCompetitions().enqueue(
                new Callback<CompetitionsData>() {
                    @Override
                    public void onResponse(
                            @NonNull Call<CompetitionsData> call,
                            @NonNull Response<CompetitionsData> response
                    ) {
                        List<Competition> insertedCompetitions = new ArrayList<>();
                        HashMap<Long, Competition> updatedCompetitions = new HashMap<>();
                        HashMap<Long, Competition> deletedCompetitions = new HashMap<>();

                        if (response.body() != null) {
                            dataHolder.mergeCompetitions(
                                    insertedCompetitions,
                                    updatedCompetitions,
                                    deletedCompetitions,
                                    response.body()
                            );

                            mergeData(
                                    new MergeDataObject(
                                            insertedCompetitions,
                                            updatedCompetitions,
                                            deletedCompetitions
                                    )
                            );
                        }
                    }

                    @Override
                    public void onFailure(Call<CompetitionsData> call, Throwable t) {

                    }
                }
        );
    }

    private class MergeDataObject{
        private List<Competition> insertedCompetitions;
        private HashMap<Long, Competition> updatedCompetitions;
        private HashMap<Long, Competition> deletedCompetitions;

        MergeDataObject(
                List<Competition> insertedCompetitions,
                HashMap<Long, Competition> updatedCompetitions,
                HashMap<Long, Competition> deletedCompetitions
        ) {
            this.insertedCompetitions = insertedCompetitions;
            this.updatedCompetitions = updatedCompetitions;
            this.deletedCompetitions = deletedCompetitions;
        }

        public List<Competition> getInsertedCompetitions() {
            return insertedCompetitions;
        }

        public HashMap<Long, Competition> getUpdatedCompetitions() {
            return updatedCompetitions;
        }

        public HashMap<Long, Competition> getDeletedCompetitions() {
            return deletedCompetitions;
        }
    }
}
