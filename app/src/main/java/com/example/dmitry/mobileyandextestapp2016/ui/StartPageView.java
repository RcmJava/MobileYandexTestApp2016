package com.example.dmitry.mobileyandextestapp2016.ui;

import com.example.dmitry.mobileyandextestapp2016.entities.Artist;

import java.util.List;


public interface StartPageView {
    void showLoading();
    void hideLoading();

    void showContent(List<Artist> list);

    void showError(int strErrorId);
}
