package com.micmr0.currencyapp.storage;

import android.database.sqlite.SQLiteDatabase;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.micmr0.currencyapp.storage.model.Currency;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

public class DatabaseStorage extends OrmLiteSqliteOpenHelper implements Storage {
    private static final String DATABASE_NAME = "currencies.db";
    private static final int DATABASE_VERSION = 1;
    private Dao<Currency, Integer> currencyDao = null;

    @Inject
    public DatabaseStorage(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Currency.class);
        } catch (SQLException e) {
            Log.e(DatabaseStorage.class.getSimpleName(), "Failed to create table: ", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Currency.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseStorage.class.getSimpleName(), "Failed to drop table: ", e);
        }
    }

    public Dao<Currency, Integer> getCurrencyDao() throws SQLException {
        if (currencyDao == null) {
            currencyDao = getDao(Currency.class);
        }
        return currencyDao;
    }

    @Override
    public void close() {
        super.close();
        currencyDao = null;
    }

    @Override
    public List<Currency> getCurrencies() throws SQLException {
        return getCurrencyDao().queryForAll();
    }
}