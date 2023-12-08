package com.example.retrofit.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcelable;

import androidx.annotation.NonNull;


public class Movie implements Parcelable {

    @SerializedName("_id")
    public Integer _id;

    @SerializedName("backdrop_path")
    public String backdrop_path;

    @SerializedName("genres")
    public List<String> genres;

    @SerializedName("original_title")
    public String original_title;

    @SerializedName("overview")
    public String overview;

    @SerializedName("poster_path")
    public String poster_path;

    @SerializedName("release_date")
    public String release_date;

    @SerializedName("title")
    public String title;

    @SerializedName("contentType")
    public String contentType;


    public Movie(Integer _id, String backdrop_path, List<String> genres, String original_title, String overview, String poster_path, String release_date, String title, String contentType) {
        this._id = _id;
        this.backdrop_path = backdrop_path;
        this.genres = genres;
        this.original_title = original_title;
        this.overview = overview;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.title = title;
        this.contentType = contentType;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }


    public String getTitle() {
        return title;
    }

    public String getThumbnailUrl() {
        return poster_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(this._id);
        parcel.writeString(this.backdrop_path);
        parcel.writeList(this.genres);
        parcel.writeString(this.original_title);
        parcel.writeString(this.overview);
        parcel.writeString(this.poster_path);
        parcel.writeString(this.release_date);
        parcel.writeString(this.title);
        parcel.writeString(this.contentType);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Movie(Parcel in) {
        this.set_id(in.readInt());
        this.setBackdrop_path(in.readString());
        List<String> genres = new ArrayList<>();
        in.readList(genres, String.class.getClassLoader());
        this.setGenres(genres);
        this.setOriginal_title(in.readString());
        this.setOverview(in.readString());
        this.setPoster_path(in.readString());
        this.setRelease_date(in.readString());
        this.setTitle(in.readString());
        this.setContentType(in.readString());
    }
}
