package com.example.moviedatabase;

import retrofit2.Call;
import retrofit2.http.GET;

public interface movieDbApi {

    String API_KEY = "2fd776bb24719f9e146e173e22207383";

    @GET("movie/popular?api_key=" + API_KEY + "&language=en-US&page=1")
    Call<MovieList> getMovieList();

}
