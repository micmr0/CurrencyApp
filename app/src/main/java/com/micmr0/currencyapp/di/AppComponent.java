package com.micmr0.currencyapp.di;

import android.content.Context;

import com.micmr0.currencyapp.App;
import com.micmr0.currencyapp.worker.CurrencyUpdateWorker;
import com.micmr0.currencyapp.MainActivity;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class, StorageModule.class, WorkerModule.class, WorkerFactoryModule.class})
public interface AppComponent {
    void inject(App application);
    void inject(CurrencyUpdateWorker worker);
    void inject(MainActivity activity);

    @Component.Factory
    interface Factory {
        AppComponent create(@BindsInstance Context context);
    }
}