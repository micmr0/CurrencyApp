package com.micmr0.currencyapp;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;

import com.micmr0.currencyapp.api.CurrencyModel;
import com.micmr0.currencyapp.api.CurrencyRepository;
import com.micmr0.currencyapp.api.CurrencyService;
import com.micmr0.currencyapp.storage.DatabaseStorage;
import com.micmr0.currencyapp.storage.model.Currency;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;

@RunWith(MockitoJUnitRunner.class)
public class MainViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    @Mock
    private CurrencyService currencyService;
    @Mock
    private Call<CurrencyModel> mockCall;
    private CurrencyRepository currencyRepository;
    @Mock
    private DatabaseStorage databaseStorage;

    private MainViewModel viewModel;


    @Before
    public void setUp() {
        currencyRepository = new CurrencyRepository(currencyService);

        when(currencyService.getChf()).thenReturn(mockCall);
        when(currencyService.getUsd()).thenReturn(mockCall);
    }

    @Test
    public void testNotEmptyCurrencyData() throws SQLException {
        when(databaseStorage.getCurrencies()).thenReturn(Collections.singletonList(new Currency()));
        viewModel = new MainViewModel(currencyRepository, databaseStorage);

        List<Currency> mockCurrencies = Collections.singletonList(new Currency());
        LiveData<List<Currency>> liveData = viewModel.getCurrencies();

        assertNotNull(liveData.getValue());
        assertEquals(mockCurrencies, liveData.getValue());
    }

    @Test
    public void testEmptyCurrencyData() throws SQLException {
        when(databaseStorage.getCurrencies()).thenReturn(Collections.emptyList());
        viewModel = new MainViewModel(currencyRepository, databaseStorage);

        List<Currency> mockCurrencies = Collections.emptyList();
        LiveData<List<Currency>> liveData = viewModel.getCurrencies();

        assertEquals(mockCurrencies, liveData.getValue());
    }
}