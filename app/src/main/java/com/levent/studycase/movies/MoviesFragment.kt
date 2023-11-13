package com.levent.studycase.movies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.levent.studycase.databinding.FragmentMoviesBinding
import com.levent.studycase.models.Movie
import com.levent.studycase.retrofit.RetrofitInstance
import com.levent.studycase.models.MoviesResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMoviesBinding.inflate(inflater, container, false)

        sendMovieRequest()

        return binding.root

    }

    private fun setupRV(responseMovies: List<Movie>) {
        val recyclerView = binding.rvMovies
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        val adapter = MovieAdapter(requireContext(),responseMovies)
        recyclerView.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun sendMovieRequest() {
        RetrofitInstance.api.getMovies("8fecf6ebc185f71c41e1d64795d5a953").enqueue(object : Callback<MoviesResult>{
            override fun onResponse(call: Call<MoviesResult>, response: Response<MoviesResult>) {

                if (response.body() != null) {
                    Log.d("TEST","SUCCESS"+response.body())

                    val responseMovies: List<Movie> = response.body()!!.results

                    setupRV(responseMovies)

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}