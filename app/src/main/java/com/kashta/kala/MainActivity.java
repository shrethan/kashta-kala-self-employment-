package com.kashta.kala;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kashta.kala.databinding.ActivityMainBinding;
import com.kashta.kala.fragments.CatalogFragment;
import com.kashta.kala.fragments.DashboardFragment;
import com.kashta.kala.fragments.EstimatorFragment;
import com.kashta.kala.fragments.PortfolioFragment;
import com.kashta.kala.fragments.QuotesFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Load default fragment
        loadFragment(new DashboardFragment());

        binding.bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_dashboard)  return loadFragment(new DashboardFragment());
            if (id == R.id.nav_catalog)    return loadFragment(new CatalogFragment());
            if (id == R.id.nav_estimator)  return loadFragment(new EstimatorFragment());
            if (id == R.id.nav_quotes)     return loadFragment(new QuotesFragment());
            if (id == R.id.nav_portfolio)  return loadFragment(new PortfolioFragment());
            return false;
        });
    }

    private boolean loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
        return true;
    }
}
