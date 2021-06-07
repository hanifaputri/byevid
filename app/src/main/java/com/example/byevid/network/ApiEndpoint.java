package com.example.byevid.network;

import com.example.byevid.model.Statistic;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpoint {
    @GET("indonesia")
    Call<List<Statistic>> getData();
}
