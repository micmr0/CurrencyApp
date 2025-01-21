package com.micmr0.currencyapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.micmr0.currencyapp.utils.CurrencyAdapter;

import java.util.Collections;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    protected MainViewModel mainViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getAppComponent().inject(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CurrencyAdapter currencyAdapter = new CurrencyAdapter(MainActivity.this, Collections.emptyList());
        recyclerView.setAdapter(currencyAdapter);

        mainViewModel.getCurrencies().observe(this, currencyAdapter::setData);
    }
}