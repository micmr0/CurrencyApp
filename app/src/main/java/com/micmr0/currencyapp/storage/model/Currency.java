package com.micmr0.currencyapp.storage.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

@DatabaseTable(tableName = "currencies")
public class Currency {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    @DatabaseField
    private String code;

    @DatabaseField
    private float rates;

    //downloadDate in sec
    @DatabaseField(columnName = "download_date")
    private long downloadDate;

    public Currency() {}

    public void fillData(String name, String code, float rates) {
        this.name = name;
        this.code = code;
        this.rates = rates;
        this.downloadDate = System.currentTimeMillis() / 1000;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;

        return Objects.equals(this.id, currency.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getRates() {
        return rates;
    }

    public void setRates(float rates) {
        this.rates = rates;
    }

    public long getDownloadDate() {
        return downloadDate;
    }

    public void setDownloadDate(long downloadDate) {
        this.downloadDate = downloadDate;
    }
}