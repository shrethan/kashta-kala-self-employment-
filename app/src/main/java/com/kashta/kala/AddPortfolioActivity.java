package com.kashta.kala;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.kashta.kala.database.entities.PortfolioItem;
import com.kashta.kala.databinding.ActivityAddPortfolioBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddPortfolioActivity extends AppCompatActivity {

    private ActivityAddPortfolioBinding binding;
    private KashtaViewModel viewModel;

    private static final String[] CATEGORIES = {"Sofa", "Bed", "Cabinet", "Table", "Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPortfolioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(KashtaViewModel.class);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Add Portfolio Item");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ArrayAdapter<String> catAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, CATEGORIES);
        binding.spinnerCategory.setAdapter(catAdapter);

        // Preview image from URL
        binding.btnPreviewImage.setOnClickListener(v -> {
            String url = binding.etImageUrl.getText().toString().trim();
            if (!url.isEmpty()) {
                Glide.with(this).load(url)
                        .placeholder(R.drawable.ic_wood_placeholder)
                        .error(R.drawable.ic_wood_placeholder)
                        .centerCrop()
                        .into(binding.ivPreview);
                binding.ivPreview.setVisibility(android.view.View.VISIBLE);
            }
        });

        binding.btnSavePortfolio.setOnClickListener(v -> saveItem());
    }

    private void saveItem() {
        String title = binding.etTitle.getText().toString().trim();
        if (title.isEmpty()) {
            Toast.makeText(this, "Title is required", Toast.LENGTH_SHORT).show();
            return;
        }

        String desc     = binding.etDescription.getText().toString().trim();
        String imageUrl = binding.etImageUrl.getText().toString().trim();
        String category = (String) binding.spinnerCategory.getSelectedItem();
        String date     = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());

        PortfolioItem item = new PortfolioItem(title, desc, imageUrl, category, date);
        viewModel.insertPortfolioItem(item);

        Toast.makeText(this, "Added to portfolio!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() { finish(); return true; }
}
