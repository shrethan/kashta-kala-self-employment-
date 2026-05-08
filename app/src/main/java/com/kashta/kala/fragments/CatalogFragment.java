package com.kashta.kala.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.chip.Chip;
import com.kashta.kala.KashtaViewModel;
import com.kashta.kala.adapters.DesignAdapter;
import com.kashta.kala.databinding.FragmentCatalogBinding;

public class CatalogFragment extends Fragment {

    private FragmentCatalogBinding binding;
    private KashtaViewModel viewModel;
    private DesignAdapter adapter;

    private static final String[] CATEGORIES = {"All", "Sofa", "Bed", "Cabinet", "Table", "❤️ Favs"};

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCatalogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(KashtaViewModel.class);

        // RecyclerView setup - Grid with 2 columns
        adapter = new DesignAdapter(design -> viewModel.toggleFavorite(design));
        binding.recyclerDesigns.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        binding.recyclerDesigns.setAdapter(adapter);

        // Observe filtered designs
        viewModel.filteredDesigns.observe(getViewLifecycleOwner(), designs -> {
            adapter.submitList(designs);
            binding.tvCount.setText(designs != null ? designs.size() + " designs" : "0 designs");
        });

        // Category chips
        for (String cat : CATEGORIES) {
            Chip chip = new Chip(requireContext());
            chip.setText(cat);
            chip.setCheckable(true);
            chip.setChecked(cat.equals("All"));
            chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    if (cat.equals("❤️ Favs")) {
                        observeFavorites();
                    } else {
                        observeCategory(cat);
                    }
                }
            });
            binding.chipGroupCategories.addView(chip);
        }
    }

    private void observeCategory(String category) {
        viewModel.setCategory(category);
        viewModel.filteredDesigns.observe(getViewLifecycleOwner(), designs -> {
            adapter.submitList(designs);
        });
    }

    private void observeFavorites() {
        viewModel.favoriteDesigns.observe(getViewLifecycleOwner(), designs -> {
            adapter.submitList(designs);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
