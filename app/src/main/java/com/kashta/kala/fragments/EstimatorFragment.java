package com.kashta.kala.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kashta.kala.databinding.FragmentEstimatorBinding;
import com.kashta.kala.utils.WoodHelper;

public class EstimatorFragment extends Fragment {

    private FragmentEstimatorBinding binding;

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentEstimatorBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Populate wood type spinner
        String[] woodNames = WoodHelper.getWoodNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item, woodNames);
        binding.spinnerWoodType.setAdapter(adapter);

        // Update wood info when selection changes
        binding.spinnerWoodType.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View v, int pos, long id) {
                String woodName = woodNames[pos];
                WoodHelper.WoodType wood = WoodHelper.WOOD_TYPES.get(woodName);
                if (wood != null) {
                    binding.tvWoodInfo.setText(
                            "₹" + wood.pricePerSqFt + "/sqft  |  Durability: " + wood.durability +
                            "  |  Density: " + wood.density);
                    binding.tvWoodInfo.setVisibility(View.VISIBLE);
                }
            }
            @Override public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });

        // Calculate button
        binding.btnCalculate.setOnClickListener(v -> calculateEstimate());

        // Reset button
        binding.btnReset.setOnClickListener(v -> {
            binding.etLength.setText("");
            binding.etWidth.setText("");
            binding.etHeight.setText("");
            binding.etLaborRate.setText("200");
            binding.cardResult.setVisibility(View.GONE);
        });
    }

    private void calculateEstimate() {
        String lengthStr = binding.etLength.getText().toString().trim();
        String widthStr  = binding.etWidth.getText().toString().trim();
        String heightStr = binding.etHeight.getText().toString().trim();
        String laborStr  = binding.etLaborRate.getText().toString().trim();

        if (lengthStr.isEmpty() || widthStr.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter length and width", Toast.LENGTH_SHORT).show();
            return;
        }

        double length = Double.parseDouble(lengthStr);
        double width  = Double.parseDouble(widthStr);
        double height = heightStr.isEmpty() ? 0 : Double.parseDouble(heightStr);
        int laborRate = laborStr.isEmpty() ? 200 : Integer.parseInt(laborStr);

        String woodType = (String) binding.spinnerWoodType.getSelectedItem();
        WoodHelper.EstimateResult result = WoodHelper.calculate(length, width, height, woodType, laborRate);

        if (result == null) {
            Toast.makeText(requireContext(), "Invalid wood type", Toast.LENGTH_SHORT).show();
            return;
        }

        // Display results
        binding.tvResultWoodType.setText(woodType);
        binding.tvResultSqFt.setText(String.format("%.2f sq ft", result.squareFeet));
        if (result.cubicFeet > 0) {
            binding.tvResultCuFt.setText(String.format("%.2f cu ft", result.cubicFeet));
            binding.rowCuFt.setVisibility(View.VISIBLE);
        } else {
            binding.rowCuFt.setVisibility(View.GONE);
        }
        binding.tvResultWoodCost.setText(String.format("₹%.0f", result.woodCost));
        binding.tvResultLaborCost.setText(String.format("₹%.0f", result.laborCost));
        binding.tvResultTotal.setText(String.format("₹%.0f", result.totalCost));
        binding.tvResultRate.setText("₹" + result.pricePerSqFt + "/sqft");

        binding.cardResult.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
