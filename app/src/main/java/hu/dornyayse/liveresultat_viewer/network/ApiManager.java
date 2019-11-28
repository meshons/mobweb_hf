package hu.dornyayse.liveresultat_viewer.network;

import hu.dornyayse.liveresultat_viewer.network.model.ClassesData;
import hu.dornyayse.liveresultat_viewer.network.model.CompetitionsData;
import hu.dornyayse.liveresultat_viewer.network.model.LastPassingsData;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private static final String SERVICE_URL = "https://liveresultat.orientering.se";

    private static ApiManager instance;

    public static ApiManager getInstance() {
        if (instance == null) {
            instance = new ApiManager();
        }
        return instance;
    }

    private Retrofit retrofit;
    private Api api;

    private ApiManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(SERVICE_URL)
                .client(new OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);
    }

    public Call<CompetitionsData> getCompetitions() {
        return api.getCompetitions();
    }

    public Call<CompetitionsData> getCompetitionInfo() {
        return api.getCompetitionInfo();
    }

    public Call<LastPassingsData> getLastPassings(
            Integer competitionId,
            String lastHash
    ) {
        return api.getLastPassings(competitionId, lastHash);
    }

    public Call<ClassesData> getClasses(
            Integer competitionId,
            String lastHash
    ) {
        return api.getClasses(competitionId, lastHash);
    }

    public Call<ClassesData> getClassResults(
            Integer competitionId,
            Boolean unformattedTimes,
            String className,
            String lastHash
    ) {
        return api.getClassResults(competitionId, unformattedTimes, className, lastHash);
    }

    public Call<ClassesData> getClubResults(
            Integer competitionId,
            Boolean unformattedTimes,
            String clubName,
            String lastHash
    ) {
        return api.getClubResults(competitionId, unformattedTimes, clubName, lastHash);
    }
}
