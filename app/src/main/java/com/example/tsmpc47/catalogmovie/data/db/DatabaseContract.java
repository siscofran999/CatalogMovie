package com.example.tsmpc47.catalogmovie.data.db;

import android.provider.BaseColumns;

public class DatabaseContract {

    public static String Table_Name_Movie_Favorite = "movie_favorite";

    public static final class MovieColumnsFavorite implements BaseColumns {

        public static String gambar = "gambar";

        public static String judul = "judul";

        public static String deskripsi = "deskripsi";

        public static String tgl = "tgl";

    }
}
