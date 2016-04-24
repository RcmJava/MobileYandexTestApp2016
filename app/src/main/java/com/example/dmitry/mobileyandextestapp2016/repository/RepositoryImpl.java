package com.example.dmitry.mobileyandextestapp2016.repository;

import com.example.dmitry.mobileyandextestapp2016.R;
import com.example.dmitry.mobileyandextestapp2016.entities.Artist;
import com.example.dmitry.mobileyandextestapp2016.interactor.GetArtistInteractor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RepositoryImpl implements IRepository {
    private RetrofitYandexApi mYandexApi;

    public RepositoryImpl() {
        mYandexApi = ApiFactory.createService(RetrofitYandexApi.class);
    }

    @Override
    public void getArtists(final GetArtistInteractor interactor) {
        Call<List<Artist>> call = mYandexApi.getArtists();

        //выполняем запрос асинхронно
        call.enqueue(new Callback<List<Artist>>() {
            @Override
            public void onResponse(Call<List<Artist>> call, Response<List<Artist>> response) {
                if (response.isSuccessful()) {
                    List<Artist> user = response.body();
                    interactor.set(user);
                } else {
                    interactor.error(R.string.error_loading);
                }
            }

            @Override
            public void onFailure(Call<List<Artist>> call, Throwable t) {
                // handle execution failures like no internet connectivity
                interactor.error(R.string.error_loading);
            }
        });
    }
}
