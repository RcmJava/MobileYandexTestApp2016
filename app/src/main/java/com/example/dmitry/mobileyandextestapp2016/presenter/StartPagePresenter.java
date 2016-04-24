package com.example.dmitry.mobileyandextestapp2016.presenter;

import android.content.Context;

import com.example.dmitry.mobileyandextestapp2016.entities.Artist;
import com.example.dmitry.mobileyandextestapp2016.interactor.GetArtistInteractor;
import com.example.dmitry.mobileyandextestapp2016.interactor.GetArtistsUseCase;
import com.example.dmitry.mobileyandextestapp2016.repository.RepositoryImpl;
import com.example.dmitry.mobileyandextestapp2016.ui.StartPageView;

import java.util.List;


public class StartPagePresenter {
    private Context mAppContext;
    //View
    private StartPageView mView;
    //Use case our application
    private final GetArtistInteractor mGetArtists;

    public StartPagePresenter(Context appContext) {
        mAppContext = appContext;
        mGetArtists = new GetArtistsUseCase(this, new RepositoryImpl());
    }

    public void attachView(StartPageView view) {
        mView = view;
    }

    public void detachView() {
        mView = null;
    }

    public void loadArtists() {
        mView.showLoading();
        mGetArtists.get();
    }

    //Callback from Use Case
    public void onFinishedLoading(List<Artist> list) {
        if (mView != null) {
            mView.hideLoading();
            mView.showContent(list);
        }
    }

    public void onError(int errorId) {
        if (mView != null) {
            mView.hideLoading();
            mView.showError(errorId);
        }
    }
}
