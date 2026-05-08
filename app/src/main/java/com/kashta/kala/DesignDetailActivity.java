package com.kashta.kala;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.kashta.kala.databinding.ActivityDesignDetailBinding;

public class DesignDetailActivity extends AppCompatActivity {

    private ActivityDesignDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDesignDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        // Get data from Intent
        String name = getIntent().getStringExtra("design_name");
        String category = getIntent().getStringExtra("design_category");
        String description = getIntent().getStringExtra("design_desc");
        String imageUrl = getIntent().getStringExtra("design_image");

        // Set UI
        binding.tvDetailName.setText(name);
        binding.tvDetailCategory.setText(category);
        binding.tvDetailDescription.setText(description);

        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.ic_wood_placeholder)
                .error(R.drawable.ic_wood_placeholder)
                .into(binding.ivDesignLarge);
    }
}
