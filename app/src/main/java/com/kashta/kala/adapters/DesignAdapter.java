package com.kashta.kala.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.kashta.kala.DesignDetailActivity;
import com.kashta.kala.R;
import com.kashta.kala.database.entities.Design;
import com.kashta.kala.databinding.ItemDesignBinding;

public class DesignAdapter extends ListAdapter<Design, DesignAdapter.DesignViewHolder> {

    public interface OnFavoriteClick { void onToggle(Design design); }
    private final OnFavoriteClick favoriteClick;

    public DesignAdapter(OnFavoriteClick favoriteClick) {
        super(DIFF_CALLBACK);
        this.favoriteClick = favoriteClick;
    }

    private static final DiffUtil.ItemCallback<Design> DIFF_CALLBACK = new DiffUtil.ItemCallback<Design>() {
        @Override public boolean areItemsTheSame(@NonNull Design a, @NonNull Design b) { return a.id == b.id; }
        @Override public boolean areContentsTheSame(@NonNull Design a, @NonNull Design b) {
            return a.name.equals(b.name) && a.isFavorited == b.isFavorited;
        }
    };

    @NonNull @Override
    public DesignViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDesignBinding binding = ItemDesignBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new DesignViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DesignViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class DesignViewHolder extends RecyclerView.ViewHolder {
        private final ItemDesignBinding binding;

        DesignViewHolder(ItemDesignBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Design design) {
            binding.tvDesignName.setText(design.name);
            binding.tvCategory.setText(design.category);
            binding.tvDescription.setText(design.description);
            binding.btnFavorite.setText(design.isFavorited ? "❤️" : "🤍");

            Glide.with(binding.getRoot())
                    .load(design.imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.ic_wood_placeholder)
                    .error(R.drawable.ic_wood_placeholder)
                    .centerCrop()
                    .into(binding.ivDesign);

            binding.btnFavorite.setOnClickListener(v -> favoriteClick.onToggle(design));

            // Item click to open Detail Activity
            binding.getRoot().setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), DesignDetailActivity.class);
                intent.putExtra("design_name", design.name);
                intent.putExtra("design_category", design.category);
                intent.putExtra("design_desc", design.description);
                intent.putExtra("design_image", design.imageUrl);
                v.getContext().startActivity(intent);
            });
        }
    }
}
