package com.kashta.kala.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.kashta.kala.R;
import com.kashta.kala.database.entities.PortfolioItem;
import com.kashta.kala.databinding.ItemPortfolioBinding;

public class PortfolioAdapter extends ListAdapter<PortfolioItem, PortfolioAdapter.PortfolioViewHolder> {

    public interface OnLongClick { void onLongPress(PortfolioItem item); }
    private final OnLongClick longClick;

    public PortfolioAdapter(OnLongClick longClick) {
        super(DIFF_CALLBACK);
        this.longClick = longClick;
    }

    private static final DiffUtil.ItemCallback<PortfolioItem> DIFF_CALLBACK = new DiffUtil.ItemCallback<>() {
        @Override public boolean areItemsTheSame(@NonNull PortfolioItem a, @NonNull PortfolioItem b) { return a.id == b.id; }
        @Override public boolean areContentsTheSame(@NonNull PortfolioItem a, @NonNull PortfolioItem b) { return a.id == b.id; }
    };

    @NonNull @Override
    public PortfolioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPortfolioBinding binding = ItemPortfolioBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new PortfolioViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PortfolioViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class PortfolioViewHolder extends RecyclerView.ViewHolder {
        private final ItemPortfolioBinding binding;

        PortfolioViewHolder(ItemPortfolioBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(PortfolioItem item) {
            binding.tvTitle.setText(item.title);
            binding.tvCategory.setText(item.category != null ? item.category : "");
            binding.tvDescription.setText(item.description != null ? item.description : "");

            if (item.imageUri != null && !item.imageUri.isEmpty()) {
                Glide.with(binding.getRoot())
                        .load(item.imageUri)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .placeholder(R.drawable.ic_wood_placeholder)
                        .centerCrop()
                        .into(binding.ivPortfolio);
            } else {
                binding.ivPortfolio.setImageResource(R.drawable.ic_wood_placeholder);
            }

            binding.getRoot().setOnLongClickListener(v -> {
                longClick.onLongPress(item);
                return true;
            });
        }
    }
}
