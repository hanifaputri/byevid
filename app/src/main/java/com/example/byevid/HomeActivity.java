package com.example.byevid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.byevid.model.Statistic;
import com.example.byevid.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private final String TAG = "HomeActivity";
    private TextView tx_positive, tx_recovered, tx_dead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tx_positive = findViewById(R.id.tv_home_stat_positive);
        tx_recovered = findViewById(R.id.tv_home_stat_recovered);
        tx_dead = findViewById(R.id.tv_home_stat_death);

        getDataFromApi();
    }


    private void getDataFromApi() {
        ApiService.endpoint().getData()
            .enqueue(new Callback<List<Statistic>>() {
                @Override
                public void onResponse(Call<List<Statistic>> call, Response<List<Statistic>> response) {
                    Log.d(TAG, response.toString());
                    if (response.isSuccessful()) {
                        List<Statistic> results = response.body();
                        showResult(results);
                        Log.d(TAG, results.toString());
                    }
                }

                @Override
                public void onFailure(Call<List<Statistic>> call, Throwable t) {
                    Log.d(TAG, t.toString());
                }
            });
    }

    private void showResult(List<Statistic> statModel) {
        Statistic result = statModel.get(0);
        tx_positive.setText(result.getPositif());
        tx_recovered.setText(result.getSembuh());
        tx_dead.setText(result.getMeninggal());
    }

}