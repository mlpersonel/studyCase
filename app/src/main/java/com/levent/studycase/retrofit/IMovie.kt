package com.levent.studycase.retrofit

import com.levent.studycase.models.MoviesResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IMovie {

    // BASE URL https://api.themoviedb.org/3
    //https://api.themoviedb.org/3/movie/popular
    // https://api.themoviedb.org/3/movie/{movie_id}

    @GET("popular")
    fun getMovies(@Query("api_key") apiKey: String,): Call<MoviesResult>

}