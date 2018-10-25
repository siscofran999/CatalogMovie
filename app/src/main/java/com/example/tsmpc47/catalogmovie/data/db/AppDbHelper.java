package com.example.tsmpc47.catalogmovie.data.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.tsmpc47.catalogmovie.data.model.Result;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

import static android.provider.BaseColumns._ID;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.CONTENT_URI;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.bintang;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.deskripsi;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.fovorite;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.gambar;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.judul;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.popular;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.tgl;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.Table_Name_Movie_Favorite;

@Singleton
public class AppDbHelper implements DbHelper {

    private final Context mContext;
    private SQLiteDatabase mSQLiteDatabase;
    private DatabaseHelper mDatabaseHelper;
    private static final String TAG = "AppDbHelper";

    public AppDbHelper(Context context){
        this.mContext = context;
    }

    @Inject
    public AppDbHelper(Context context, DatabaseHelper databaseHelper){
        this.mContext = context;
        this.mDatabaseHelper = databaseHelper;
    }

    @Override
    public AppDbHelper openDB() throws SQLException {
        mDatabaseHelper = new DatabaseHelper(mContext);
        mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();
        return this;
    }

    @Override
    public void closeDb() {
        mDatabaseHelper.close();
    }


    @Override
    public Cursor queryProvider() {
        return mSQLiteDatabase.query(Table_Name_Movie_Favorite,null,null,null,null,null,_ID + " DESC", null);
    }

    @Override
    public Cursor queryByIdProvider(String id) {
        Log.i(TAG, "queryByIdProvider: "+id);
        return mSQLiteDatabase.query(Table_Name_Movie_Favorite, null,
                _ID + " = ?",
                new String[]{id},
                null,
                null,
                null,
                null);
    }

    @Override
    public long insertProviders(ContentValues contentValues) {
        Log.i(TAG, "insertProviders: "+contentValues.get(judul));
        return mSQLiteDatabase.insert(Table_Name_Movie_Favorite,null,contentValues);
    }

    @Override
    public int updateProvider(String id, ContentValues contentValues) {
        return mSQLiteDatabase.update(Table_Name_Movie_Favorite, contentValues, _ID + " = ?", new String[]{id});
    }

    @Override
    public int deleteProviders(String id) {
        Log.i(TAG, "deleteProviders: "+id);
        return mSQLiteDatabase.delete(Table_Name_Movie_Favorite,_ID + " = ?", new String[]{id});
    }

}
