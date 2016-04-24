package com.example.dmitry.mobileyandextestapp2016.repository;

import com.example.dmitry.mobileyandextestapp2016.entities.Artist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface RetrofitYandexApi {
    @GET("download.cdn.yandex.net/mobilization-2016/artists.json")
    Call<List<Artist>> getArtists();
}
