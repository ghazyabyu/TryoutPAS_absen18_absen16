package com.example.tryoutpas_absen18_absen16;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("search_all_teams.php?l=English%20Premier%20League")
    Call<TeamResponse> getEnglishPremierleague(@Query("las") String league);

    @GET("search_all_teams.php?l=Spanish%20La%20Liga")
    Call<TeamResponse> getLaliga(@Query("las") String league); // FIXED!
}