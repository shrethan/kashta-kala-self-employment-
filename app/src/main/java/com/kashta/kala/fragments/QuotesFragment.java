package com.kashta.kala.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.kashta.kala.KashtaViewModel;
import com.kashta.kala.AddQuoteActivity;
import com.kashta.kala.adapters.QuoteAdapter;
import com.kashta.kala.databinding.FragmentQuotesBinding;

import android.content.Intent;

public class QuotesFragment extends Fragment {

    private FragmentQuotesBinding binding;
    private KashtaViewModel viewModel;
    private QuoteAdapter adapter;

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentQuotesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(KashtaViewModel.class);

        adapter = new QuoteAdapter(quote -> {
            new MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Delete Quote")
                    .setMessage("Delete quote for " + quote.customerName + "?")
                    .setPositiveButton("Delete", (d, w) -> viewModel.deleteQuote(quote))
                    .setNegativeButton("Cancel", null)
                    .show();
        });

        binding.recyclerQuotes.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerQuotes.setAdapter(adapter);

        viewModel.allQuotes.observe(getViewLifecycleOwner(), quotes -> {
            adapter.submitList(quotes);
            if (quotes == null || quotes.isEmpty()) {
                binding.layoutEmpty.setVisibility(View.VISIBLE);
                binding.recyclerQuotes.setVisibility(View.GONE);
                binding.cardSummary.setVisibility(View.GONE);
            } else {
                binding.layoutEmpty.setVisibility(View.GONE);
                binding.recyclerQuotes.setVisibility(View.VISIBLE);
                binding.cardSummary.setVisibility(View.VISIBLE);

                double total = 0;
                for (var q : quotes) total += q.totalCost;
                binding.tvSummaryCount.setText(quotes.size() + " quotes");
                binding.tvSummaryTotal.setText(String.format("₹%.0f", total));
                binding.tvSummaryAvg.setText(String.format("₹%.0f avg", total / quotes.size()));
            }
        });

        binding.fabAddQuote.setOnClickListener(v ->
                startActivity(new Intent(requireContext(), AddQuoteActivity.class)));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
