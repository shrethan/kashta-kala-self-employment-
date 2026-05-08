package com.kashta.kala;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.kashta.kala.database.KashtaDatabase;
import com.kashta.kala.database.dao.DesignDao;
import com.kashta.kala.database.dao.PortfolioDao;
import com.kashta.kala.database.dao.PriceQuoteDao;
import com.kashta.kala.database.entities.Design;
import com.kashta.kala.database.entities.PortfolioItem;
import com.kashta.kala.database.entities.PriceQuote;

import java.util.List;

public class KashtaRepository {

    private final DesignDao designDao;
    private final PriceQuoteDao quoteDao;
    private final PortfolioDao portfolioDao;

    public final LiveData<List<Design>> allDesigns;
    public final LiveData<List<Design>> favoriteDesigns;
    public final LiveData<List<PriceQuote>> allQuotes;
    public final LiveData<List<PortfolioItem>> allPortfolioItems;

    public KashtaRepository(Application application) {
        KashtaDatabase db = KashtaDatabase.getInstance(application);
        designDao   = db.designDao();
        quoteDao    = db.priceQuoteDao();
        portfolioDao = db.portfolioDao();

        allDesigns        = designDao.getAllDesigns();
        favoriteDesigns   = designDao.getFavoriteDesigns();
        allQuotes         = quoteDao.getAllQuotes();
        allPortfolioItems = portfolioDao.getAllItems();
    }

    // Designs
    public LiveData<List<Design>> getDesignsByCategory(String category) {
        return designDao.getDesignsByCategory(category);
    }

    public void toggleFavorite(Design design) {
        KashtaDatabase.databaseWriteExecutor.execute(() ->
                designDao.setFavorite(design.id, !design.isFavorited));
    }

    // Quotes
    public void insertQuote(PriceQuote quote) {
        KashtaDatabase.databaseWriteExecutor.execute(() -> quoteDao.insert(quote));
    }

    public void deleteQuote(PriceQuote quote) {
        KashtaDatabase.databaseWriteExecutor.execute(() -> quoteDao.delete(quote));
    }

    // Portfolio
    public void insertPortfolioItem(PortfolioItem item) {
        KashtaDatabase.databaseWriteExecutor.execute(() -> portfolioDao.insert(item));
    }

    public void deletePortfolioItem(PortfolioItem item) {
        KashtaDatabase.databaseWriteExecutor.execute(() -> portfolioDao.delete(item));
    }
}
