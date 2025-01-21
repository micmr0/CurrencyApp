package com.micmr0.currencyapp.di;

import androidx.work.Worker;

import com.micmr0.currencyapp.worker.DaggerWorkerFactory;

import java.util.Map;

import javax.inject.Provider;

import dagger.Module;
import dagger.Provides;

@Module
public class WorkerFactoryModule {
    @Provides
    public static DaggerWorkerFactory provideWorkerFactory(Map<String, Provider<Worker>> workers) {
        return new DaggerWorkerFactory(workers);
    }
}