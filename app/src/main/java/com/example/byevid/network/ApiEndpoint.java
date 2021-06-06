package com.example.byevid.network;

import com.example.byevid.model.StatModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpoint {
    @GET("indonesia")
    Call<List<StatModel>> getData();
}
