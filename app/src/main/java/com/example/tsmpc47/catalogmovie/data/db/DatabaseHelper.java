package com.example.tsmpc47.catalogmovie.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tsmpc47.catalogmovie.utils.AppConstants;

import static android.provider.BaseColumns._ID;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.bintang;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.deskripsi;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.fovorite;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.gambar;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.judul;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.popular;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.tgl;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.Table_Name_Movie_Favorite;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = AppConstants.DB_NAME;

    private static final int DATABASE_VERSION = 1;

//    private static String CREATE_TABLE_MOVIE_FAVORITE = "create table " + Table_Name_Movie_Favorite +
//            " (" + _ID + " integer primary key autoincrement, "
//            +gambar + " varchar(50) not null, "
//            +deskripsi + " varchar(50) not null, "
//            +tgl + " varchar(30) not null, "
//            +judul + " varchar(100) not null,"
//            +bintang + " varchar(30) not null,"
//            +popular + " varchar(30) not null,"
//            +fovorite + " varchar(30) not null);";

    private static final String CREATE_TABLE_MOVIE_FAVORITE = String.format("CREATE TABLE %s" +
                    "(%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL)",
            DatabaseContract.Table_Name_Movie_Favorite,
            DatabaseContract.MovieColumnsFavorite._ID,
            DatabaseContract.MovieColumnsFavorite.gambar,
            DatabaseContract.MovieColumnsFavorite.judul,
            DatabaseContract.MovieColumnsFavorite.deskripsi,
            DatabaseContract.MovieColumnsFavorite.tgl,
            DatabaseContract.MovieColumnsFavorite.bintang,
            DatabaseContract.MovieColumnsFavorite.popular,
            DatabaseContract.MovieColumnsFavorite.fovorite

//            "CREATE TABLE " + USER_TABLE + "(" + USER + " TEXT, " + POSITION + " TEXT )";

    );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MOVIE_FAVORITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name_Movie_Favorite);
        onCreate(db);
    }
}
