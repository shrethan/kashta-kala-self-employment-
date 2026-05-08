package com.kashta.kala.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.kashta.kala.database.dao.DesignDao;
import com.kashta.kala.database.dao.DesignDao_Impl;
import com.kashta.kala.database.dao.PortfolioDao;
import com.kashta.kala.database.dao.PortfolioDao_Impl;
import com.kashta.kala.database.dao.PriceQuoteDao;
import com.kashta.kala.database.dao.PriceQuoteDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class KashtaDatabase_Impl extends KashtaDatabase {
  private volatile DesignDao _designDao;

  private volatile PriceQuoteDao _priceQuoteDao;

  private volatile PortfolioDao _portfolioDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `designs` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `category` TEXT, `image_url` TEXT, `description` TEXT, `is_favorited` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `price_quotes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `customer_name` TEXT, `design_name` TEXT, `wood_type` TEXT, `length_ft` REAL NOT NULL, `width_ft` REAL NOT NULL, `height_ft` REAL NOT NULL, `square_feet` REAL NOT NULL, `wood_cost` REAL NOT NULL, `labor_cost` REAL NOT NULL, `total_cost` REAL NOT NULL, `notes` TEXT, `created_at` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `portfolio` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT, `description` TEXT, `image_uri` TEXT, `category` TEXT, `created_at` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'df1e8f66321ddd7b4887047bc042dadb')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `designs`");
        db.execSQL("DROP TABLE IF EXISTS `price_quotes`");
        db.execSQL("DROP TABLE IF EXISTS `portfolio`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsDesigns = new HashMap<String, TableInfo.Column>(6);
        _columnsDesigns.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDesigns.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDesigns.put("category", new TableInfo.Column("category", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDesigns.put("image_url", new TableInfo.Column("image_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDesigns.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDesigns.put("is_favorited", new TableInfo.Column("is_favorited", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDesigns = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDesigns = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDesigns = new TableInfo("designs", _columnsDesigns, _foreignKeysDesigns, _indicesDesigns);
        final TableInfo _existingDesigns = TableInfo.read(db, "designs");
        if (!_infoDesigns.equals(_existingDesigns)) {
          return new RoomOpenHelper.ValidationResult(false, "designs(com.kashta.kala.database.entities.Design).\n"
                  + " Expected:\n" + _infoDesigns + "\n"
                  + " Found:\n" + _existingDesigns);
        }
        final HashMap<String, TableInfo.Column> _columnsPriceQuotes = new HashMap<String, TableInfo.Column>(13);
        _columnsPriceQuotes.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceQuotes.put("customer_name", new TableInfo.Column("customer_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceQuotes.put("design_name", new TableInfo.Column("design_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceQuotes.put("wood_type", new TableInfo.Column("wood_type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceQuotes.put("length_ft", new TableInfo.Column("length_ft", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceQuotes.put("width_ft", new TableInfo.Column("width_ft", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceQuotes.put("height_ft", new TableInfo.Column("height_ft", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceQuotes.put("square_feet", new TableInfo.Column("square_feet", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceQuotes.put("wood_cost", new TableInfo.Column("wood_cost", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceQuotes.put("labor_cost", new TableInfo.Column("labor_cost", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceQuotes.put("total_cost", new TableInfo.Column("total_cost", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceQuotes.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceQuotes.put("created_at", new TableInfo.Column("created_at", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPriceQuotes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPriceQuotes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPriceQuotes = new TableInfo("price_quotes", _columnsPriceQuotes, _foreignKeysPriceQuotes, _indicesPriceQuotes);
        final TableInfo _existingPriceQuotes = TableInfo.read(db, "price_quotes");
        if (!_infoPriceQuotes.equals(_existingPriceQuotes)) {
          return new RoomOpenHelper.ValidationResult(false, "price_quotes(com.kashta.kala.database.entities.PriceQuote).\n"
                  + " Expected:\n" + _infoPriceQuotes + "\n"
                  + " Found:\n" + _existingPriceQuotes);
        }
        final HashMap<String, TableInfo.Column> _columnsPortfolio = new HashMap<String, TableInfo.Column>(6);
        _columnsPortfolio.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPortfolio.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPortfolio.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPortfolio.put("image_uri", new TableInfo.Column("image_uri", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPortfolio.put("category", new TableInfo.Column("category", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPortfolio.put("created_at", new TableInfo.Column("created_at", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPortfolio = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPortfolio = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPortfolio = new TableInfo("portfolio", _columnsPortfolio, _foreignKeysPortfolio, _indicesPortfolio);
        final TableInfo _existingPortfolio = TableInfo.read(db, "portfolio");
        if (!_infoPortfolio.equals(_existingPortfolio)) {
          return new RoomOpenHelper.ValidationResult(false, "portfolio(com.kashta.kala.database.entities.PortfolioItem).\n"
                  + " Expected:\n" + _infoPortfolio + "\n"
                  + " Found:\n" + _existingPortfolio);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "df1e8f66321ddd7b4887047bc042dadb", "e213cc78d38d9093ed3d7fdacfe0dba6");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "designs","price_quotes","portfolio");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `designs`");
      _db.execSQL("DELETE FROM `price_quotes`");
      _db.execSQL("DELETE FROM `portfolio`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(DesignDao.class, DesignDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PriceQuoteDao.class, PriceQuoteDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PortfolioDao.class, PortfolioDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public DesignDao designDao() {
    if (_designDao != null) {
      return _designDao;
    } else {
      synchronized(this) {
        if(_designDao == null) {
          _designDao = new DesignDao_Impl(this);
        }
        return _designDao;
      }
    }
  }

  @Override
  public PriceQuoteDao priceQuoteDao() {
    if (_priceQuoteDao != null) {
      return _priceQuoteDao;
    } else {
      synchronized(this) {
        if(_priceQuoteDao == null) {
          _priceQuoteDao = new PriceQuoteDao_Impl(this);
        }
        return _priceQuoteDao;
      }
    }
  }

  @Override
  public PortfolioDao portfolioDao() {
    if (_portfolioDao != null) {
      return _portfolioDao;
    } else {
      synchronized(this) {
        if(_portfolioDao == null) {
          _portfolioDao = new PortfolioDao_Impl(this);
        }
        return _portfolioDao;
      }
    }
  }
}
