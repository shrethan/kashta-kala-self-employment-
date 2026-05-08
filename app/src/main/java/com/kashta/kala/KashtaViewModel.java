package com.kashta.kala;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.kashta.kala.database.entities.Design;
import com.kashta.kala.database.entities.PortfolioItem;
import com.kashta.kala.database.entities.PriceQuote;

import java.util.List;

public class KashtaViewModel extends AndroidViewModel {

    private final KashtaRepository repository;

    public final LiveData<List<Design>> allDesigns;
    public final LiveData<List<Design>> favoriteDesigns;
    public final LiveData<List<PriceQuote>> allQuotes;
    public final LiveData<List<PortfolioItem>> allPortfolioItems;

    private final MutableLiveData<String> selectedCategory = new MutableLiveData<>("All");
    public final LiveData<List<Design>> filteredDesigns;

    public KashtaViewModel(Application application) {
        super(application);
        repository = new KashtaRepository(application);

        allDesigns        = repository.allDesigns;
        favoriteDesigns   = repository.favoriteDesigns;
        allQuotes         = repository.allQuotes;
        allPortfolioItems = repository.allPortfolioItems;

        filteredDesigns = Transformations.switchMap(selectedCategory, category -> {
            if (category == null || category.equals("All")) {
                return repository.allDesigns;
            } else {
                return repository.getDesignsByCategory(category);
            }
        });
    }

    public void setCategory(String category) { selectedCategory.setValue(category); }
    public void toggleFavorite(Design design) { repository.toggleFavorite(design); }
    public void insertQuote(PriceQuote quote) { repository.insertQuote(quote); }
    public void deleteQuote(PriceQuote quote) { repository.deleteQuote(quote); }
    public void insertPortfolioItem(PortfolioItem item) { repository.insertPortfolioItem(item); }
    public void deletePortfolioItem(PortfolioItem item) { repository.deletePortfolioItem(item); }
}
