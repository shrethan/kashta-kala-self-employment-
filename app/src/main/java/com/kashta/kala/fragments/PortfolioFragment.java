package com.kashta.kala.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.kashta.kala.AddPortfolioActivity;
import com.kashta.kala.KashtaViewModel;
import com.kashta.kala.adapters.PortfolioAdapter;
import com.kashta.kala.databinding.FragmentPortfolioBinding;

public class PortfolioFragment extends Fragment {

    private FragmentPortfolioBinding binding;
    private KashtaViewModel viewModel;
    private PortfolioAdapter adapter;

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPortfolioBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(KashtaViewModel.class);

        adapter = new PortfolioAdapter(item -> new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Remove Item")
                .setMessage("Remove \"" + item.title + "\" from portfolio?")
                .setPositiveButton("Remove", (d, w) -> viewModel.deletePortfolioItem(item))
                .setNegativeButton("Cancel", null)
                .show());

        binding.recyclerPortfolio.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        binding.recyclerPortfolio.setAdapter(adapter);

        viewModel.allPortfolioItems.observe(getViewLifecycleOwner(), items -> {
            adapter.submitList(items);
            binding.layoutEmpty.setVisibility(items == null || items.isEmpty() ? View.VISIBLE : View.GONE);
            binding.recyclerPortfolio.setVisibility(items != null && !items.isEmpty() ? View.VISIBLE : View.GONE);
        });

        binding.fabAddPortfolio.setOnClickListener(v ->
                startActivity(new Intent(requireContext(), AddPortfolioActivity.class)));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
