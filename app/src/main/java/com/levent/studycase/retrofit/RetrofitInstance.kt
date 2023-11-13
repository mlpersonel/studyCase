package com.levent.studycase.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {

     val api: IMovie by lazy {
         Retrofit.Builder()
             .baseUrl("https://api.themoviedb.org/3/movie/")
             .addConverterFactory(GsonConverterFactory.create())
             .build()
             .create(IMovie::class.java)
     }

}