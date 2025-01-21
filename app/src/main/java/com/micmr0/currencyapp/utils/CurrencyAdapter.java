package com.micmr0.currencyapp.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.micmr0.currencyapp.R;
import com.micmr0.currencyapp.storage.model.Currency;

import java.util.ArrayList;
import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder> {
    private final Context context;
    private List<Currency> data;

    public CurrencyAdapter(Context context, List<Currency> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameCodeTextView.setText(String.format("%1$s (%2$s)", data.get(position).getName(), data.get(position).getCode()));
        holder.ratesTextView.setText(String.format(context.getString(R.string.rates_label), data.get(position).getRates()));
        holder.updateTimeTextView.setText(String.format(context.getString(R.string.update_time_label), DateUtil.getDateLabel(data.get(position).getDownloadDate())));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Currency> currencies) {
        data.clear();
        data = new ArrayList<>(currencies);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameCodeTextView;
        public TextView ratesTextView;
        public TextView updateTimeTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameCodeTextView = itemView.findViewById(R.id.name_code_text_view);
            ratesTextView = itemView.findViewById(R.id.rates_text_view);
            updateTimeTextView = itemView.findViewById(R.id.update_date_text_view);
        }
    }
}
