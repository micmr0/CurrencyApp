package com.micmr0.currencyapp;

import android.app.Application;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.micmr0.currencyapp.di.AppComponent;
import com.micmr0.currencyapp.di.DaggerAppComponent;
import com.micmr0.currencyapp.worker.CurrencyUpdateWorker;

public class App extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.factory().create(getApplicationContext());
        appComponent.inject(this);

        scheduleWork();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    private void scheduleWork() {
        OneTimeWorkRequest initialWorkRequest = new OneTimeWorkRequest.Builder(CurrencyUpdateWorker.class)
                .build();
        WorkManager.getInstance(getApplicationContext()).enqueue(initialWorkRequest);
    }
}