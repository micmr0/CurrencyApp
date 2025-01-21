package com.micmr0.currencyapp.worker;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.j256.ormlite.dao.Dao;
import com.micmr0.currencyapp.App;
import com.micmr0.currencyapp.api.CurrencyModel;
import com.micmr0.currencyapp.api.CurrencyModelCallback;
import com.micmr0.currencyapp.api.CurrencyRepository;
import com.micmr0.currencyapp.storage.DatabaseStorage;
import com.micmr0.currencyapp.storage.model.Currency;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

public class CurrencyUpdateWorker extends Worker {
    public static final String WORK_NAME = "CurrencyUpdate";

    @Inject
    protected CurrencyRepository currencyRepository;
    @Inject
    protected DatabaseStorage databaseStorage;

    @Inject
    public CurrencyUpdateWorker(Context context, WorkerParameters workerParams) {
        super(context, workerParams);

        ((App) getApplicationContext()).getAppComponent().inject(this);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(CurrencyUpdateWorker.class.getSimpleName(), "Worker is running...");

        updateCurrencies();

        // do next task
        OneTimeWorkRequest nextWorkRequest = new OneTimeWorkRequest.Builder(CurrencyUpdateWorker.class)
                .setInitialDelay(1, TimeUnit.MINUTES) // 1 minute delay
                .build();
        WorkManager.getInstance(getApplicationContext()).enqueueUniqueWork(WORK_NAME, ExistingWorkPolicy.REPLACE, nextWorkRequest);

        return Result.success();
    }

    private void updateCurrencies() {
        currencyRepository.getChf().enqueue((new CurrencyModelCallback(this::updateCurrencyData)));
        currencyRepository.getUsd().enqueue(new CurrencyModelCallback(this::updateCurrencyData));
    }

    private void updateCurrencyData(CurrencyModel currencyModel) throws SQLException {
        Dao<Currency, Integer> currencyDao = databaseStorage.getCurrencyDao();

        List<Currency> currencies = currencyDao.queryForEq("code", currencyModel.getCode());

        if (!currencies.isEmpty()) {
            Currency currency = currencies.get(0);
            currency.fillData(currencyModel.getCurrency(), currencyModel.getCode(), currencyModel.getRates().get(0).getAsk());

            currencyDao.update(currency);

        } else {
            Currency newCurrency = new Currency();
            newCurrency.fillData(currencyModel.getCurrency(), currencyModel.getCode(), currencyModel.getRates().get(0).getAsk());
            currencyDao.create(newCurrency);
        }
    }

}