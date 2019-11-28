package hu.dornyayse.liveresultat_viewer.network;

import hu.dornyayse.liveresultat_viewer.network.model.ClassesData;
import hu.dornyayse.liveresultat_viewer.network.model.CompetitionsData;
import hu.dornyayse.liveresultat_viewer.network.model.LastPassingsData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("api.php?Method=getcompetitions")
    Call<CompetitionsData> getCompetitions();

    @GET("api.php?Method=getcompetitioninfo")
    Call<CompetitionsData> getCompetitionInfo();

    @GET("api.php?Method=getlastpassings")
    Call<LastPassingsData> getLastPassings(
            @Query("comp") Integer competitionId,
            @Query("last_hash") String lastHash
    );

    @GET("api.php?Method=getclasses")
    Call<ClassesData> getClasses(
            @Query("comp") Integer competitionId,
            @Query("last_hash") String lastHash
    );

    @GET("api.php?Method=getclassresults")
    Call<ClassesData> getClassResults(
            @Query("comp") Integer competitionId,
            @Query("unformattedTimes") Boolean unformattedTimes,
            @Query("class") String className,
            @Query("last_hash") String lastHash
    );

    @GET("api.php?Method=getclubresults")
    Call<ClassesData> getClubResults(
            @Query("comp") Integer competitionId,
            @Query("unformattedTimes") Boolean unformattedTimes,
            @Query("club") String clubName,
            @Query("last_hash") String lastHash
    );
}
