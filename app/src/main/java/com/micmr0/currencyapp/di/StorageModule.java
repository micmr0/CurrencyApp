package com.micmr0.currencyapp.di;

import com.micmr0.currencyapp.storage.DatabaseStorage;
import com.micmr0.currencyapp.storage.Storage;

import dagger.Binds;
import dagger.Module;

@Module
abstract class StorageModule {
    @Binds
    abstract Storage provideStorage(DatabaseStorage storage);
}