package com.example.dmitry.mobileyandextestapp2016.interactor;

import com.example.dmitry.mobileyandextestapp2016.entities.Artist;

import java.util.List;


public interface GetArtistInteractor {
    void get();
    void set(List<Artist> list);
    void error(int errorId);
}
