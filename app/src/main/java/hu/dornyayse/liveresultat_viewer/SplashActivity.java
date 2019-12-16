package hu.dornyayse.liveresultat_viewer;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import hu.dornyayse.liveresultat_viewer.model.Competition;
import hu.dornyayse.liveresultat_viewer.service.DataHolder;
import hu.dornyayse.liveresultat_viewer.service.ServiceLocator;

public class SplashActivity extends AppCompatActivity {

    private DataHolder dataHolder = ServiceLocator.getInstance().getDataHolder();
    private TextView loaderTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loaderTextView = findViewById(R.id.loading);

        new LoadData(this).execute();
    }

    private static class LoadData extends AsyncTask<Void, Integer, HashMap<Long, Competition>> {

        private WeakReference<SplashActivity> activityReference;

        LoadData(SplashActivity activity) {
            this.activityReference = new WeakReference<SplashActivity>(activity);
        }

        @Override
        protected HashMap<Long, Competition> doInBackground(Void... voids) {
            SplashActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return null;

            BackgroundLoaderCount blc = new BackgroundLoaderCount(this);
            activity.dataHolder.load(activity.getApplicationContext(), blc);
            return activity.dataHolder.getCompetitions();
        }

        @Override
        protected void onPostExecute(HashMap<Long, Competition> loadedCompetitions) {
            SplashActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;

            Intent i = new Intent(activity, CompetitionListActivity.class);
            activity.startActivity(i);
            activity.finish();
        }

        @Override
        protected void onProgressUpdate (Integer... values) {
            SplashActivity activity = activityReference.get();

            activity.loaderTextView.setText(
                    activity.getResources().getString(R.string.loading, values[0], values[1])
            );
        }

        void publish(BackgroundLoaderCount blc) {
            publishProgress(blc.getCurrentProgress(), blc.getNumberOfCompetitions());
        }
    }

    public static class BackgroundLoaderCount {
        private int numberOfCompetitions = 0;
        private int currentProgress = 0;
        private LoadData asyncTask;

        BackgroundLoaderCount(LoadData asyncTask) {
            this.asyncTask = asyncTask;
        }

        int getNumberOfCompetitions() {
            return numberOfCompetitions;
        }

        public void setNumberOfCompetitions(int numberOfCompetitions) {
            this.numberOfCompetitions = numberOfCompetitions;
        }

        int getCurrentProgress() {
            return currentProgress;
        }

        public void setCurrentProgress(int currentProgress) {
            this.currentProgress = currentProgress;
        }

        public void increaseProgress() {
            currentProgress++;
            asyncTask.publish(this);
        }
    }
}
