package com.example.dmitry.mobileyandextestapp2016.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import nl.qbusict.cupboard.annotation.Column;


public class Artist implements Parcelable {
    @Column("id")
    public Long _id; // for cupboard
    public String name;
    public List<String> genres;
    public int tracks;
    public int albums;
    public String link;
    public String description;
    public Cover cover;

    public Artist() {
        // for cupboard
    }

    protected Artist(Parcel in) {
        _id = in.readLong();
        name = in.readString();
        genres = in.createStringArrayList();
        tracks = in.readInt();
        albums = in.readInt();
        link = in.readString();
        description = in.readString();
        cover = in.readParcelable(Cover.class.getClassLoader());
    }

    public static final Creator<Artist> CREATOR = new Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel in) {
            return new Artist(in);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(_id);
        dest.writeString(name);
        dest.writeStringList(genres);
        dest.writeInt(tracks);
        dest.writeInt(albums);
        dest.writeString(link);
        dest.writeString(description);
        dest.writeParcelable(cover, flags);
    }
}
