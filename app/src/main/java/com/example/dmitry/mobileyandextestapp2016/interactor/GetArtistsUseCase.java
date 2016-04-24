package com.example.dmitry.mobileyandextestapp2016.interactor;

import com.example.dmitry.mobileyandextestapp2016.entities.Artist;
import com.example.dmitry.mobileyandextestapp2016.presenter.StartPagePresenter;
import com.example.dmitry.mobileyandextestapp2016.repository.IRepository;

import java.util.List;


public class GetArtistsUseCase implements GetArtistInteractor {
    private final IRepository mRepository;
    private final StartPagePresenter mPresenter;

    public GetArtistsUseCase(StartPagePresenter presenter, IRepository repository) {
        mPresenter = presenter;
        mRepository = repository;
    }

    @Override
    public void get() {
        mRepository.getArtists(this);
    }

    @Override
    public void set(List<Artist> list) {
        mPresenter.onFinishedLoading(list);
    }

    @Override
    public void error(int errorId) {
        mPresenter.onError(errorId);
    }
}
