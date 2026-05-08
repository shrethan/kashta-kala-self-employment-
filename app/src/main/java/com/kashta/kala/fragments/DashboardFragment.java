package com.kashta.kala.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.kashta.kala.KashtaViewModel;
import com.kashta.kala.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private KashtaViewModel viewModel;

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(KashtaViewModel.class);

        viewModel.allDesigns.observe(getViewLifecycleOwner(), designs -> {
            binding.tvTotalDesigns.setText(String.valueOf(designs != null ? designs.size() : 0));
        });

        viewModel.favoriteDesigns.observe(getViewLifecycleOwner(), favs -> {
            binding.tvFavDesigns.setText(String.valueOf(favs != null ? favs.size() : 0));
        });

        viewModel.allQuotes.observe(getViewLifecycleOwner(), quotes -> {
            binding.tvTotalQuotes.setText(String.valueOf(quotes != null ? quotes.size() : 0));
            double total = 0;
            if (quotes != null) for (var q : quotes) total += q.totalCost;
            binding.tvTotalRevenue.setText(String.format("₹%.0f", total));
        });

        viewModel.allPortfolioItems.observe(getViewLifecycleOwner(), items -> {
            binding.tvPortfolioCount.setText(String.valueOf(items != null ? items.size() : 0));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
