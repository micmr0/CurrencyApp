package com.micmr0.currencyapp.api;

import android.util.Log;

import androidx.annotation.NonNull;

import java.sql.SQLException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyModelCallback implements Callback<CurrencyModel> {
    private final UpdateCurrencyDataFunction updateCurrencyDataFunction;

    public CurrencyModelCallback(UpdateCurrencyDataFunction updateFunction) {
        this.updateCurrencyDataFunction = updateFunction;
    }

    @Override
    public void onResponse
            (@NonNull Call<CurrencyModel> call, @NonNull Response<CurrencyModel> response) {
        if (response.isSuccessful()) {
            if (response.body() != null) {
                try {
                    updateCurrencyDataFunction.update(response.body());
                } catch (SQLException e) {
                    Log.e(CurrencyModelCallback.class.getSimpleName(), "Failed to save currency", e);
                }
            }
        }
    }

    @Override
    public void onFailure(@NonNull Call<CurrencyModel> call, @NonNull Throwable t) {
        Log.e(CurrencyModelCallback.class.getSimpleName(), "onFailure");
    }

    @FunctionalInterface
    public interface UpdateCurrencyDataFunction {
        void update(CurrencyModel currencyModel) throws SQLException;
    }
}