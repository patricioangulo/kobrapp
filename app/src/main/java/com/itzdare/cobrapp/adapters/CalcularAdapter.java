package com.itzdare.cobrapp.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalcularAdapter extends RecyclerView.Adapter<CalcularAdapter.viewHolderAdapter> {





    @NonNull
    @Override
    public viewHolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderAdapter holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class viewHolderAdapter extends RecyclerView.ViewHolder {
        public viewHolderAdapter(@NonNull View itemView) {
            super(itemView);
        }
    }
}
