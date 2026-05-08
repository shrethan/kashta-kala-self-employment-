package com.kashta.kala.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.kashta.kala.database.entities.PriceQuote;
import com.kashta.kala.databinding.ItemQuoteBinding;

public class QuoteAdapter extends ListAdapter<PriceQuote, QuoteAdapter.QuoteViewHolder> {

    public interface OnDeleteClick { void onDelete(PriceQuote quote); }
    private final OnDeleteClick deleteClick;

    public QuoteAdapter(OnDeleteClick deleteClick) {
        super(DIFF_CALLBACK);
        this.deleteClick = deleteClick;
    }

    private static final DiffUtil.ItemCallback<PriceQuote> DIFF_CALLBACK = new DiffUtil.ItemCallback<>() {
        @Override public boolean areItemsTheSame(@NonNull PriceQuote a, @NonNull PriceQuote b) { return a.id == b.id; }
        @Override public boolean areContentsTheSame(@NonNull PriceQuote a, @NonNull PriceQuote b) { return a.id == b.id; }
    };

    @NonNull @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemQuoteBinding binding = ItemQuoteBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new QuoteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class QuoteViewHolder extends RecyclerView.ViewHolder {
        private final ItemQuoteBinding binding;

        QuoteViewHolder(ItemQuoteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(PriceQuote quote) {
            binding.tvCustomerName.setText(quote.customerName);
            binding.tvDesignName.setText(quote.designName);
            binding.tvWoodType.setText(quote.woodType);
            binding.tvDimensions.setText(String.format("%.1f ft × %.1f ft  =  %.1f sq ft",
                    quote.lengthFt, quote.widthFt, quote.squareFeet));
            binding.tvWoodCost.setText(String.format("₹%.0f", quote.woodCost));
            binding.tvLaborCost.setText(String.format("₹%.0f", quote.laborCost));
            binding.tvTotalCost.setText(String.format("₹%.0f", quote.totalCost));
            binding.tvDate.setText(quote.createdAt);
            if (quote.notes != null && !quote.notes.isEmpty()) {
                binding.tvNotes.setText("📝 " + quote.notes);
                binding.tvNotes.setVisibility(android.view.View.VISIBLE);
            } else {
                binding.tvNotes.setVisibility(android.view.View.GONE);
            }
            binding.btnDelete.setOnClickListener(v -> deleteClick.onDelete(quote));
        }
    }
}
