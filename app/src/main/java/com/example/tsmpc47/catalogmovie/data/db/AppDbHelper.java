package com.example.tsmpc47.catalogmovie.data.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.tsmpc47.catalogmovie.data.model.Result;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

import static android.provider.BaseColumns._ID;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.deskripsi;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.gambar;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.judul;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.tgl;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.Table_Name_Movie_Favorite;

@Singleton
public class AppDbHelper implements DbHelper {

    private final Context mContext;
    private SQLiteDatabase mSQLiteDatabase;
    private DatabaseHelper mDatabaseHelper;
    private static final String TAG = "AppDbHelper";

    @Inject
    public AppDbHelper(Context context, DatabaseHelper databaseHelper){
        this.mContext = context;
        this.mDatabaseHelper = databaseHelper;
    }

    @Override
    public AppDbHelper openDB() throws SQLException {
        mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();
        return this;
    }

    @Override
    public void closeDb() {
        mDatabaseHelper.close();
    }

    @Override
    public void insertDB(String img, String title, String overview, String date) {
        openDB();
        beginTransaction();
        insertTransaction(img,title,overview,date);
    }

    @Override
    public int searchData(String title) {
        Log.i(TAG, "searchData: "+title);
        Cursor cursor = mSQLiteDatabase.query(Table_Name_Movie_Favorite,null, judul +" LIKE ?",
                new String[]{title + "%"},null,null,_ID + " ASC",null);
        cursor.moveToFirst();
        Log.i(TAG, "searchData: "+cursor.getCount());

        return cursor.getCount();
    }


    private void insertTransaction(String img, String title, String overview, String date) {
        Log.i(TAG, "insertTransaction img: "+img);
        Log.i(TAG, "insertTransaction title: "+title);
        Log.i(TAG, "insertTransaction overview: "+overview);
        Log.i(TAG, "insertTransaction date: "+date);
        String sql;
        sql = "INSERT INTO "+ Table_Name_Movie_Favorite +"("+ gambar +", "+ judul +", "+ deskripsi +", "+ tgl+") VALUES (?,?,?,?)";
        SQLiteStatement stmt = mSQLiteDatabase.compileStatement(sql);
        stmt.bindString(1, img);
        stmt.bindString(2, title);
        stmt.bindString(3, overview);
        stmt.bindString(4, date);
        stmt.execute();
        stmt.clearBindings();
        Log.i(TAG, "insertTransaction: Sukses");
    }

    private void beginTransaction() {
        mSQLiteDatabase.beginTransaction();
    }
}
