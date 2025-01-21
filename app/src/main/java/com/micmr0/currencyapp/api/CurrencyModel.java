package com.micmr0.currencyapp.api;

import java.util.List;

public class CurrencyModel {
    private int id;
    private String table;
    private String currency;
    private String code;
    private List<RateModel> rates;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<RateModel> getRates() {
        return rates;
    }

    public void setRates(List<RateModel> rates) {
        this.rates = rates;
    }
}