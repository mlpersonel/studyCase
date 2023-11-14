package com.levent.studycase.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.levent.studycase.models.Movie
import com.levent.studycase.models.MoviesResult
import com.levent.studycase.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel(): ViewModel() {
    private var responseMoviesLiveData = MutableLiveData<List<Movie>>()
      fun sendMovieRequest() {
        RetrofitInstance.api.getMovies("8fecf6ebc185f71c41e1d64795d5a953").enqueue(object :
            Callback<MoviesResult> {
            override fun onResponse(call: Call<MoviesResult>, response: Response<MoviesResult>) {

                if (response.body() != null) {
                    Log.d("TEST","SUCCESS"+response.body())

                    val responseMovies: List<Movie> = response.body()!!.results

                    responseMoviesLiveData.postValue(response.body()!!.results)

                    Log.d("TEST","SUCCESS"+responseMovies.size)

                } else {
                    Log.d("TEST","SUCCESS"+response.body())
                }
            }

            override fun onFailure(call: Call<MoviesResult>, t: Throwable) {
                Log.d("TEST","Failure"+t.message)
            }
        })
    }

    fun observeMoviesLiveData(): LiveData<List<Movie>>{
        return responseMoviesLiveData
    }

}