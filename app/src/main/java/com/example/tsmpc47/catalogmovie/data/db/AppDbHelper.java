package com.example.tsmpc47.catalogmovie.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.tsmpc47.catalogmovie.data.model.Result;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

import static android.provider.BaseColumns._ID;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.bintang;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.deskripsi;
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
        mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();
        return this;
    }

    @Override
    public void closeDb() {
        mDatabaseHelper.close();
    }

    @Override
    public void insertDB(String img, String title, String overview, String date, String rating, String popular) {
        openDB();
        beginTransaction();
        insertTransaction(img,title,overview,date, rating, popular);
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

    @Override
    public Observable<List<Result>> getDataFavorite() {
        openDB();
        beginTransaction();
        Cursor cursor = mSQLiteDatabase.query(Table_Name_Movie_Favorite,null,
                null,null,null,null,_ID + " ASC",null);

        Log.i(TAG, "getDataFavorite: "+cursor.getCount());
        final ArrayList<Result> arrayList = new ArrayList<>();
        Result favorite;
        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
            do {
                favorite = new Result();
                favorite.setPosterPath(cursor.getString(cursor.getColumnIndex(gambar)));
                favorite.setReleaseDate(cursor.getString(cursor.getColumnIndex(tgl)));
                favorite.setOverview(cursor.getString(cursor.getColumnIndex(deskripsi)));
                favorite.setTitle(cursor.getString(cursor.getColumnIndex(judul)));
                favorite.setVoteAverage(Double.parseDouble(cursor.getString(cursor.getColumnIndex(bintang))));
                favorite.setPopularity(Double.parseDouble((cursor.getString(cursor.getColumnIndex(popular)))));

                arrayList.add(favorite);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();

        return Observable.fromCallable(() -> arrayList);
    }

    @Override
    public void deletedFavorite(String title) {
        openDB();
        beginTransaction();
        mSQLiteDatabase.delete(Table_Name_Movie_Favorite, judul + "=?", new String[]{title});
    }

    @Override
    public Cursor queryProvider() {
        return mSQLiteDatabase.query(Table_Name_Movie_Favorite,null,null,null,null,null,_ID + " DESC");
    }

    @Override
    public Cursor queryByIdProvider(String title) {
        return mSQLiteDatabase.query(Table_Name_Movie_Favorite,null,judul + " = ?", new String[]{title},null,null,null,null);
    }

    @Override
    public long insertProvider(ContentValues contentValues) {
        return mSQLiteDatabase.insert(Table_Name_Movie_Favorite,null,contentValues);
    }

    @Override
    public int deleteProvider(String title) {
        return mSQLiteDatabase.delete(Table_Name_Movie_Favorite,judul + " = ?", new String[]{title});
    }

    private void insertTransaction(String img, String title, String overview, String date, String rating, String popularity) {
        Log.i(TAG, "insertTransaction img: "+img);
        Log.i(TAG, "insertTransaction title: "+title);
        Log.i(TAG, "insertTransaction overview: "+overview);
        Log.i(TAG, "insertTransaction date: "+date);
        Log.i(TAG, "insertTransaction date: "+popularity);
        String sql;
        sql = "INSERT INTO "+ Table_Name_Movie_Favorite +"("+ gambar +", "+ judul +", "+ deskripsi +", "+ tgl+","+ bintang+","+ popular+") VALUES (?,?,?,?,?,?)";
        SQLiteStatement stmt = mSQLiteDatabase.compileStatement(sql);
        stmt.bindString(1, img);
        stmt.bindString(2, title);
        stmt.bindString(3, overview);
        stmt.bindString(4, date);
        stmt.bindString(5, rating);
        stmt.bindString(6, popularity);
        stmt.execute();
        stmt.clearBindings();
        Log.i(TAG, "insertTransaction: Sukses");
    }

    private void beginTransaction() {
        mSQLiteDatabase.beginTransaction();
    }


}
