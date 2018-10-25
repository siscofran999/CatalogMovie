package com.example.favorite.data.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {

    public static String Table_Name_Movie_Favorite = "movie_favorite";

    public static final String AUTHORITY = "com.example.tsmpc47.catalogmovie";

    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(Table_Name_Movie_Favorite)
            .build();

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString( cursor.getColumnIndex(columnName) );
    }

    public static final class MovieColumnsFavorite implements BaseColumns {

        public static String gambar = "gambar";

        public static String judul = "judul";

        public static String deskripsi = "deskripsi";

        public static String tgl = "tgl";

        public static String bintang = "bintang";

        public static String popular = "popular";

    }

}
