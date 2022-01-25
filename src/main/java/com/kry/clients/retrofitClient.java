package com.kry.clients;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface retrofitClient {

    @GET()
    Call<String> retrieveResponse(@Url String url);
}
