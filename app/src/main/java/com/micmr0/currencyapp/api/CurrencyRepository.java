package com.micmr0.currencyapp.api;

import javax.inject.Inject;

import retrofit2.Call;

public class CurrencyRepository {
    private final CurrencyService currencyService;

    @Inject
    public CurrencyRepository(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    public Call<CurrencyModel> getChf() {
        return currencyService.getChf();
    }

    public Call<CurrencyModel> getUsd() {
        return currencyService.getUsd();
    }
}
