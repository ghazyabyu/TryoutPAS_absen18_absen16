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

public class LaLigaFragment extends Fragment {
    RecyclerView rvlaliga;
    ProgressBar progressBar;
    Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_laliga, container, false);

        rvlaliga = view.findViewById(R.id.rvlaliga);
        progressBar = view.findViewById(R.id.progressBar);
        rvlaliga.setLayoutManager(new LinearLayoutManager(getContext()));

        APIService api = RetrofitClient.getInstance().create(APIService.class);
        fetchJadwal(api);

        return view;
    }

    private void fetchJadwal(APIService api) {
        api.getLaliga("La Liga").enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Team> jadwal = response.body().getTeams();
                    adapter = new Adapter(jadwal);
                    rvlaliga.setAdapter(adapter);
                    rvlaliga.setVisibility(View.VISIBLE);
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
