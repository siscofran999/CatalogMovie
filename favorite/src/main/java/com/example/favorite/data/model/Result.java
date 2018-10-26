package com.example.favorite.data.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import static android.provider.MediaStore.Audio.Playlists.Members._ID;
import static com.example.favorite.data.db.DatabaseContract.MovieColumnsFavorite.bintang;
import static com.example.favorite.data.db.DatabaseContract.MovieColumnsFavorite.deskripsi;
import static com.example.favorite.data.db.DatabaseContract.MovieColumnsFavorite.fovorite;
import static com.example.favorite.data.db.DatabaseContract.MovieColumnsFavorite.gambar;
import static com.example.favorite.data.db.DatabaseContract.MovieColumnsFavorite.judul;
import static com.example.favorite.data.db.DatabaseContract.MovieColumnsFavorite.popular;
import static com.example.favorite.data.db.DatabaseContract.MovieColumnsFavorite.tgl;
import static com.example.favorite.data.db.DatabaseContract.getColumnInt;
import static com.example.favorite.data.db.DatabaseContract.getColumnString;

public class Result implements Parcelable{

    private Integer id;
    private String title;
    private Double voteAverage;
    private Double popularity;
    private String posterPath;
    private String overview;
    private boolean isFavourite;
    private String releaseDate;

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Result(Parcel source){
        id = source.readInt();
        title = source.readString();
        overview = source.readString();
        posterPath = source.readString();
        releaseDate = source.readString();
        popularity = source.readDouble();
        voteAverage = source.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(posterPath);
        dest.writeString(releaseDate);
        dest.writeDouble(popularity);
        dest.writeDouble(voteAverage);
    }

    public Result(Cursor cursor){
        id = getColumnInt(cursor, _ID);
        posterPath = getColumnString(cursor, gambar);
        title = getColumnString(cursor, judul);
        overview = getColumnString(cursor, deskripsi);
        releaseDate = getColumnString(cursor, tgl);
        voteAverage = Double.valueOf(getColumnString(cursor, bintang));
        popularity = Double.valueOf(getColumnString(cursor,popular));
        isFavourite = Boolean.parseBoolean(getColumnString(cursor, fovorite));
    }
}
