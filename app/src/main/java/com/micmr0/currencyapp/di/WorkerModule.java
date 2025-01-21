package com.micmr0.currencyapp.di;

import androidx.work.Worker;
import androidx.work.WorkerFactory;

import com.micmr0.currencyapp.worker.CurrencyUpdateWorker;
import com.micmr0.currencyapp.worker.DaggerWorkerFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module
public abstract class WorkerModule {
    @Binds
    @IntoMap
    @ClassKey(CurrencyUpdateWorker.class)
    public abstract Worker bindCurrencyUpdateWorker(CurrencyUpdateWorker worker);

    @Binds
    public abstract WorkerFactory bindWorkerFactory(DaggerWorkerFactory factory);
}