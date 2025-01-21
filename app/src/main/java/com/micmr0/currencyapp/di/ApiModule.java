package com.micmr0.currencyapp.di;

import android.content.Context;

import com.micmr0.currencyapp.R;
import com.micmr0.currencyapp.api.CurrencyRepository;
import com.micmr0.currencyapp.api.CurrencyService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    @Provides
    @Singleton
    Retrofit provideRetrofit(Context context) {
        return new Retrofit.Builder()
                .baseUrl(context.getString(R.string.API_BASE_URL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    CurrencyService provideUserService(Retrofit retrofit) {
        return retrofit.create(CurrencyService.class);
    }

    @Provides
    @Singleton
    CurrencyRepository provideUserRepository(CurrencyService currencyService) {
        return new CurrencyRepository(currencyService);
    }
}