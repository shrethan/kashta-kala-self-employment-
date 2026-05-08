package com.kashta.kala;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.kashta.kala.database.entities.PriceQuote;
import com.kashta.kala.databinding.ActivityAddQuoteBinding;
import com.kashta.kala.utils.WoodHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddQuoteActivity extends AppCompatActivity {

    private ActivityAddQuoteBinding binding;
    private KashtaViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddQuoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(KashtaViewModel.class);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("New Price Quote");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Populate wood spinner
        String[] woodNames = WoodHelper.getWoodNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, woodNames);
        binding.spinnerWoodType.setAdapter(adapter);

        // Live preview
        binding.btnPreview.setOnClickListener(v -> previewEstimate());

        // Save quote
        binding.btnSaveQuote.setOnClickListener(v -> saveQuote());
    }

    private void previewEstimate() {
        try {
            double length = Double.parseDouble(binding.etLength.getText().toString());
            double width  = Double.parseDouble(binding.etWidth.getText().toString());
            double height = binding.etHeight.getText().toString().isEmpty() ? 0
                    : Double.parseDouble(binding.etHeight.getText().toString());
            String woodType = (String) binding.spinnerWoodType.getSelectedItem();

            WoodHelper.EstimateResult r = WoodHelper.calculate(length, width, height, woodType, 200);
            if (r == null) return;

            binding.tvPreviewSqFt.setText(String.format("%.2f sq ft", r.squareFeet));
            binding.tvPreviewWoodCost.setText(String.format("₹%.0f", r.woodCost));
            binding.tvPreviewLabor.setText(String.format("₹%.0f", r.laborCost));
            binding.tvPreviewTotal.setText(String.format("₹%.0f", r.totalCost));
            binding.cardPreview.setVisibility(android.view.View.VISIBLE);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Enter valid dimensions first", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveQuote() {
        String customer = binding.etCustomerName.getText().toString().trim();
        String design   = binding.etDesignName.getText().toString().trim();
        String lengthStr = binding.etLength.getText().toString().trim();
        String widthStr  = binding.etWidth.getText().toString().trim();

        if (customer.isEmpty() || design.isEmpty() || lengthStr.isEmpty() || widthStr.isEmpty()) {
            Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double length = Double.parseDouble(lengthStr);
        double width  = Double.parseDouble(widthStr);
        double height = binding.etHeight.getText().toString().isEmpty() ? 0
                : Double.parseDouble(binding.etHeight.getText().toString());
        String woodType = (String) binding.spinnerWoodType.getSelectedItem();
        String notes = binding.etNotes.getText().toString().trim();

        WoodHelper.EstimateResult r = WoodHelper.calculate(length, width, height, woodType, 200);
        if (r == null) return;

        String date = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());

        PriceQuote quote = new PriceQuote(customer, design, woodType, length, width, height,
                r.squareFeet, r.woodCost, r.laborCost, r.totalCost, notes, date);
        viewModel.insertQuote(quote);

        Toast.makeText(this, "Quote saved!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() { finish(); return true; }
}
