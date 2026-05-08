package com.kashta.kala.database.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.kashta.kala.database.entities.PriceQuote;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class PriceQuoteDao_Impl implements PriceQuoteDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PriceQuote> __insertionAdapterOfPriceQuote;

  private final EntityDeletionOrUpdateAdapter<PriceQuote> __deletionAdapterOfPriceQuote;

  public PriceQuoteDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPriceQuote = new EntityInsertionAdapter<PriceQuote>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `price_quotes` (`id`,`customer_name`,`design_name`,`wood_type`,`length_ft`,`width_ft`,`height_ft`,`square_feet`,`wood_cost`,`labor_cost`,`total_cost`,`notes`,`created_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final PriceQuote entity) {
        statement.bindLong(1, entity.id);
        if (entity.customerName == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.customerName);
        }
        if (entity.designName == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.designName);
        }
        if (entity.woodType == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.woodType);
        }
        statement.bindDouble(5, entity.lengthFt);
        statement.bindDouble(6, entity.widthFt);
        statement.bindDouble(7, entity.heightFt);
        statement.bindDouble(8, entity.squareFeet);
        statement.bindDouble(9, entity.woodCost);
        statement.bindDouble(10, entity.laborCost);
        statement.bindDouble(11, entity.totalCost);
        if (entity.notes == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.notes);
        }
        if (entity.createdAt == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.createdAt);
        }
      }
    };
    this.__deletionAdapterOfPriceQuote = new EntityDeletionOrUpdateAdapter<PriceQuote>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `price_quotes` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final PriceQuote entity) {
        statement.bindLong(1, entity.id);
      }
    };
  }

  @Override
  public void insert(final PriceQuote quote) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfPriceQuote.insert(quote);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final PriceQuote quote) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfPriceQuote.handle(quote);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<PriceQuote>> getAllQuotes() {
    final String _sql = "SELECT * FROM price_quotes ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"price_quotes"}, false, new Callable<List<PriceQuote>>() {
      @Override
      @Nullable
      public List<PriceQuote> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCustomerName = CursorUtil.getColumnIndexOrThrow(_cursor, "customer_name");
          final int _cursorIndexOfDesignName = CursorUtil.getColumnIndexOrThrow(_cursor, "design_name");
          final int _cursorIndexOfWoodType = CursorUtil.getColumnIndexOrThrow(_cursor, "wood_type");
          final int _cursorIndexOfLengthFt = CursorUtil.getColumnIndexOrThrow(_cursor, "length_ft");
          final int _cursorIndexOfWidthFt = CursorUtil.getColumnIndexOrThrow(_cursor, "width_ft");
          final int _cursorIndexOfHeightFt = CursorUtil.getColumnIndexOrThrow(_cursor, "height_ft");
          final int _cursorIndexOfSquareFeet = CursorUtil.getColumnIndexOrThrow(_cursor, "square_feet");
          final int _cursorIndexOfWoodCost = CursorUtil.getColumnIndexOrThrow(_cursor, "wood_cost");
          final int _cursorIndexOfLaborCost = CursorUtil.getColumnIndexOrThrow(_cursor, "labor_cost");
          final int _cursorIndexOfTotalCost = CursorUtil.getColumnIndexOrThrow(_cursor, "total_cost");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final List<PriceQuote> _result = new ArrayList<PriceQuote>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PriceQuote _item;
            final String _tmpCustomerName;
            if (_cursor.isNull(_cursorIndexOfCustomerName)) {
              _tmpCustomerName = null;
            } else {
              _tmpCustomerName = _cursor.getString(_cursorIndexOfCustomerName);
            }
            final String _tmpDesignName;
            if (_cursor.isNull(_cursorIndexOfDesignName)) {
              _tmpDesignName = null;
            } else {
              _tmpDesignName = _cursor.getString(_cursorIndexOfDesignName);
            }
            final String _tmpWoodType;
            if (_cursor.isNull(_cursorIndexOfWoodType)) {
              _tmpWoodType = null;
            } else {
              _tmpWoodType = _cursor.getString(_cursorIndexOfWoodType);
            }
            final double _tmpLengthFt;
            _tmpLengthFt = _cursor.getDouble(_cursorIndexOfLengthFt);
            final double _tmpWidthFt;
            _tmpWidthFt = _cursor.getDouble(_cursorIndexOfWidthFt);
            final double _tmpHeightFt;
            _tmpHeightFt = _cursor.getDouble(_cursorIndexOfHeightFt);
            final double _tmpSquareFeet;
            _tmpSquareFeet = _cursor.getDouble(_cursorIndexOfSquareFeet);
            final double _tmpWoodCost;
            _tmpWoodCost = _cursor.getDouble(_cursorIndexOfWoodCost);
            final double _tmpLaborCost;
            _tmpLaborCost = _cursor.getDouble(_cursorIndexOfLaborCost);
            final double _tmpTotalCost;
            _tmpTotalCost = _cursor.getDouble(_cursorIndexOfTotalCost);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpCreatedAt;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmpCreatedAt = null;
            } else {
              _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
            }
            _item = new PriceQuote(_tmpCustomerName,_tmpDesignName,_tmpWoodType,_tmpLengthFt,_tmpWidthFt,_tmpHeightFt,_tmpSquareFeet,_tmpWoodCost,_tmpLaborCost,_tmpTotalCost,_tmpNotes,_tmpCreatedAt);
            _item.id = _cursor.getInt(_cursorIndexOfId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public int getTotalCount() {
    final String _sql = "SELECT COUNT(*) FROM price_quotes";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if (_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public double getTotalRevenue() {
    final String _sql = "SELECT SUM(total_cost) FROM price_quotes";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final double _result;
      if (_cursor.moveToFirst()) {
        _result = _cursor.getDouble(0);
      } else {
        _result = 0.0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
