package hu.dornyayse.liveresultat_viewer;

import android.os.Bundle;
import android.view.View;

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

        Long competitionId = getIntent().getLongExtra("competition", 0);
        competition = dataHolder.getCompetition(competitionId);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(competition.getName());
        toolbar.setNavigationIcon(R.drawable.arrow_dark);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private enum SearchEnum {
        club,
        name
    }
}
