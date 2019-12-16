package hu.dornyayse.liveresultat_viewer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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

    private CompetitionAdapter competitionAdapter;

    private String searchName;
    private Date searchDate;

    private CoordinatorLayout coordinatorLayout;
    private SwipeRefreshLayout swipeRefreshLayout;

    private boolean updatingDatabase = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.app_name));

        coordinatorLayout = findViewById(R.id.coordinator_layout);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        RecyclerView competitionListView = findViewById(R.id.competition_list);
        competitionAdapter = new CompetitionAdapter();
        competitionListView.setLayoutManager(new LinearLayoutManager(this));
        competitionListView.setAdapter(competitionAdapter);

        competitions = dataHolder.getCompetitions();

        if (competitions != null) {
            List<Competition> orderedCompetition = new ArrayList<>(competitions.values());
            Collections.sort(orderedCompetition);
            competitionAdapter.update(orderedCompetition);
            List<Competition> todayCompetitions = new ArrayList<>();
            for (Competition competition : competitions.values()) {
                if (DateUtils.isToday(competition.getDate().getTime()))
                    todayCompetitions.add(competition);
            }
            Collections.sort(todayCompetitions);
            competitionAdapter.updateToday(todayCompetitions);
        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                loadFromApi();
            }
        });
        loadFromApi();

    }

    public void mergeData(
            MergeDataObject mergeDataObject
    ) {
        new MergeData(this).execute(mergeDataObject);
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

            activity.updatingDatabase = true;

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

            activity.swipeRefreshLayout.setRefreshing(false);

            List<Competition> orderedCompetition = new ArrayList<>(activity.competitions.values());
            Collections.sort(orderedCompetition);
            activity.competitionAdapter.update(orderedCompetition);
            List<Competition> todayCompetitions = new ArrayList<>();
            for (Competition competition : activity.competitions.values()) {
                if (DateUtils.isToday(competition.getDate().getTime()))
                    todayCompetitions.add(competition);
            }
            int i = 0;
            Collections.sort(todayCompetitions);
            activity.competitionAdapter.updateToday(todayCompetitions);
            activity.updatingDatabase = false;
            Snackbar.make(
                    activity.coordinatorLayout,
                    R.string.data_loaded_from_API,
                    Snackbar.LENGTH_SHORT
            ).show();
        }
    }

    private void loadFromApi() {
        if (updatingDatabase)
            return;
        swipeRefreshLayout.setRefreshing(true);
        Snackbar.make(
                coordinatorLayout,
                R.string.data_started_loading_from_API,
                Snackbar.LENGTH_SHORT
        ).show();
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
                    public void onFailure(
                            @NonNull Call<CompetitionsData> call,
                            @NonNull Throwable t
                    ) {
                        swipeRefreshLayout.setRefreshing(false);
                        Snackbar.make(
                                coordinatorLayout,
                                R.string.data_load_failed_from_API,
                                Snackbar.LENGTH_INDEFINITE
                        ).setAction(R.string.retry, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                loadFromApi();
                            }
                        }).show();
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
