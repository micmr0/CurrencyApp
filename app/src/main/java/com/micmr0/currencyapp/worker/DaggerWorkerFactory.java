package com.micmr0.currencyapp.worker;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerFactory;
import androidx.work.WorkerParameters;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

public class DaggerWorkerFactory extends WorkerFactory {
    private final Map<String, Provider<Worker>> workers;

    @Inject
    public DaggerWorkerFactory(Map<String, Provider<Worker>> workers) {
        this.workers = workers;
    }

    @Override
    public @Nullable ListenableWorker createWorker(@NonNull Context appContext, @NonNull String workerClassName, @NonNull WorkerParameters workerParameters) {
        Provider<? extends Worker> provider = workers.get(workerClassName);
        if (provider == null) {
            return null;
        }
        return provider.get();
    }
}