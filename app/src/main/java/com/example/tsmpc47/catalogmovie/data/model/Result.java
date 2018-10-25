package com.example.tsmpc47.catalogmovie.data.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.tsmpc47.catalogmovie.data.db.DbHelper;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import static android.provider.MediaStore.Audio.Playlists.Members._ID;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.bintang;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.deskripsi;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.fovorite;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.gambar;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.judul;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.popular;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.MovieColumnsFavorite.tgl;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.getColumnInt;
import static com.example.tsmpc47.catalogmovie.data.db.DatabaseContract.getColumnString;

public class Result implements Parcelable{

    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("video")
    @Expose
    private Boolean video;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = new ArrayList<>();
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;

    private boolean isFavourite;

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

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public Result(){

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
