package com.micmr0.currencyapp.storage;

import android.content.Context;
import com.micmr0.currencyapp.storage.model.Currency;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import java.sql.SQLException;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class DatabaseStorageTest {
    private DatabaseStorage databaseStorage;


    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        databaseStorage = new DatabaseStorage(context);
        databaseStorage.getWritableDatabase();
    }

    @Test
    public void testAddCurrency() {
        try {
        Currency currency = new Currency();
        databaseStorage.getCurrencyDao().create(currency);

        List<Currency> currencies = databaseStorage.getCurrencies();

        assertEquals(1, currencies.size());
        assertEquals(currency, currencies.get(0));
        } catch (SQLException e) {
            fail("SQL Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testUpdateCurrency() {
        try {
            databaseStorage.getCurrencyDao().deleteBuilder().delete(); //clear database

            Currency currency = new Currency();
            currency.fillData("dolar amerykański", "USD", 3.58f);
            databaseStorage.getCurrencyDao().create(currency);

            Currency dbCurrency = databaseStorage.getCurrencies().get(0);
            dbCurrency.setRates(3.99f);
            databaseStorage.getCurrencyDao().update(dbCurrency);

            assertEquals(3.99f, databaseStorage.getCurrencies().get(0).getRates(), 0);

        } catch (SQLException e) {
            fail("SQL Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testDeleteCurrency() {
        try {
            databaseStorage.getCurrencyDao().deleteBuilder().delete(); //clear database

            Currency currency = new Currency();
            databaseStorage.getCurrencyDao().create(currency);
            assertEquals(1, databaseStorage.getCurrencies().size());

            databaseStorage.getCurrencyDao().deleteById(currency.getId());
            assertEquals(0, databaseStorage.getCurrencies().size());

        } catch (SQLException e) {
            fail("SQL Exception occurred: " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        databaseStorage.close();
    }
}