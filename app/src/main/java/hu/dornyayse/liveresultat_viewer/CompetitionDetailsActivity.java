package hu.dornyayse.liveresultat_viewer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import hu.dornyayse.liveresultat_viewer.model.Competition;
import hu.dornyayse.liveresultat_viewer.network.ApiManager;
import hu.dornyayse.liveresultat_viewer.service.DataHolder;
import hu.dornyayse.liveresultat_viewer.service.ServiceLocator;

public class CompetitionDetailsActivity extends AppCompatActivity {

    private Competition competition;

    private DataHolder dataHolder = ServiceLocator.getInstance().getDataHolder();
    private ApiManager apiManager = ServiceLocator.getInstance().getApiManager();

    private String search;
    private SearchEnum searchEnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private enum SearchEnum {
        club,
        name
    }
}
