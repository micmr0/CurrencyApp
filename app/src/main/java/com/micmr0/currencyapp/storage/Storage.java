package com.micmr0.currencyapp.storage;

import com.micmr0.currencyapp.storage.model.Currency;

import java.sql.SQLException;
import java.util.List;

public interface Storage {
    List<Currency> getCurrencies() throws SQLException;
}
