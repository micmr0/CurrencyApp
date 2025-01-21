package com.micmr0.currencyapp.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CurrencyService {
    @GET("chf?format=json")
    Call<CurrencyModel> getChf();
    @GET("usd?format=json")
    Call<CurrencyModel> getUsd();
}
