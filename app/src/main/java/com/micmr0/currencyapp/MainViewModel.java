package com.micmr0.currencyapp;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.j256.ormlite.dao.Dao;
import com.micmr0.currencyapp.api.CurrencyModel;
import com.micmr0.currencyapp.api.CurrencyModelCallback;
import com.micmr0.currencyapp.api.CurrencyRepository;
import com.micmr0.currencyapp.storage.DatabaseStorage;
import com.micmr0.currencyapp.storage.model.Currency;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<Currency>> currencies;
    final private CurrencyRepository currencyRepository;
    final private DatabaseStorage databaseStorage;

    @Inject
    public MainViewModel(CurrencyRepository currencyRepository, DatabaseStorage databaseStorage) {
        this.currencyRepository = currencyRepository;
        this.databaseStorage = databaseStorage;

        initializeData();
    }

    public void initializeData() {
        currencies = new MutableLiveData<>();
        try {
            currencies.setValue(databaseStorage.getCurrencies());
        } catch (SQLException e) {
            Log.e(MainViewModel.class.getSimpleName(), "Failed to query currencies", e);
        }
    }

    public LiveData<List<Currency>> getCurrencies() {
        if (currencies.getValue() == null || currencies.getValue().isEmpty()) {
            firstFetchCurrencies();
        }
        return currencies;
    }

    public void firstFetchCurrencies() {
        currencyRepository.getChf().enqueue((new CurrencyModelCallback(this::saveCurrency)));
        currencyRepository.getUsd().enqueue(new CurrencyModelCallback(this::saveCurrency));
    }

    private void saveCurrency(CurrencyModel currencyModel) {
        try {
            Dao<Currency, Integer> currencyDao = databaseStorage.getCurrencyDao();

            Currency newCurrency = new Currency();
            newCurrency.fillData(currencyModel.getCurrency(), currencyModel.getCode(), currencyModel.getRates().get(0).getAsk());
            currencyDao.create(newCurrency);
            updateCurrenciesList();
        } catch (SQLException e) {
            Log.e(MainViewModel.class.getSimpleName(), "Failed to save currency", e);
        }
    }

    private void updateCurrenciesList() {
        try {
            List<Currency> list = databaseStorage.getCurrencies();
            currencies.postValue(list);
        } catch (SQLException e) {
            Log.e(MainViewModel.class.getSimpleName(), "Failed to query currencies", e);
        }
    }
}