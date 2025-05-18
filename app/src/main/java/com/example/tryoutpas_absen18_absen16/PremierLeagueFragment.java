package com.example.tryoutpas_absen18_absen16;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PremierLeagueFragment extends Fragment {
    RecyclerView rvpremiere;
    ProgressBar progressBar;
    Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_premierleague, container, false);

        rvpremiere = view.findViewById(R.id.rvpremiere);
        progressBar = view.findViewById(R.id.progressBar);
        rvpremiere.setLayoutManager(new LinearLayoutManager(getContext()));

        APIService api = RetrofitClient.getInstance().create(APIService.class);
        fetchJadwal(api);
        
        return view;
    }

    private void fetchJadwal(APIService api) {
        api.getEnglishPremierleague("Tes").enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Team> jadwal = response.body().getTeams();
                    adapter = new Adapter(jadwal);
                    rvpremiere.setAdapter(adapter);
                    rvpremiere.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                Log.e("API_ERROR", t.getMessage());
            }
        });
    }
}
