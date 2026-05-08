package com.kashta.kala.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.kashta.kala.database.dao.DesignDao;
import com.kashta.kala.database.dao.PortfolioDao;
import com.kashta.kala.database.dao.PriceQuoteDao;
import com.kashta.kala.database.entities.Design;
import com.kashta.kala.database.entities.PortfolioItem;
import com.kashta.kala.database.entities.PriceQuote;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Design.class, PriceQuote.class, PortfolioItem.class}, version = 1, exportSchema = false)
public abstract class KashtaDatabase extends RoomDatabase {

    public abstract DesignDao designDao();
    public abstract PriceQuoteDao priceQuoteDao();
    public abstract PortfolioDao portfolioDao();

    private static volatile KashtaDatabase INSTANCE;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);

    public static KashtaDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (KashtaDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    KashtaDatabase.class,
                                    "kashta_kala_db")
                            .addCallback(seedCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // Pre-seed design catalog on first launch
    private static final RoomDatabase.Callback seedCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                DesignDao dao = INSTANCE.designDao();
                dao.insert(new Design("Royal Sofa Set",      "Sofa",    "https://images.unsplash.com/photo-1555041469-a586c61ea9bc?w=800", "Elegant 3-seater royal design with carved wooden arms"));
                dao.insert(new Design("L-Shape Sofa",        "Sofa",    "https://images.unsplash.com/photo-1567016432779-094069958ea5?w=800", "Contemporary L-shaped sofa with premium cushions"));
                dao.insert(new Design("King Size Bed",       "Bed",     "https://images.unsplash.com/photo-1631049307264-da0ec9d70304?w=800", "King size bed with headboard and storage drawers"));
                dao.insert(new Design("Queen Bed Frame",     "Bed",     "https://images.unsplash.com/photo-1505693416388-ac5ce068fe85?w=800", "Queen size bed with upholstered headboard"));
                dao.insert(new Design("Wardrobe Cabinet",    "Cabinet", "https://images.unsplash.com/photo-1595428774223-ef52624120d2?w=800", "4-door wardrobe with mirror and internal shelves"));
                dao.insert(new Design("Book Shelf",          "Cabinet", "https://images.unsplash.com/photo-1555685812-4b943f1cb0eb?w=800", "5-tier bookshelf with adjustable shelves"));
                dao.insert(new Design("Dining Table Set",    "Table",   "https://images.unsplash.com/photo-1615066390971-03e4e1c36ddf?w=800", "6-seater dining table with classic wooden finish"));
                dao.insert(new Design("Coffee Table",        "Table",   "https://images.unsplash.com/photo-1532372320572-cda25653a26d?w=800", "Modern center table with glass top and wooden base"));
            });
        }
    };
}
