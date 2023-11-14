package com.levent.studycase.movies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.levent.studycase.databinding.FragmentMoviesBinding
import com.levent.studycase.models.Movie

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesViewModel = ViewModelProviders.of(this)[MoviesViewModel::class.java]
    }
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMoviesBinding.inflate(inflater, container, false)


        return binding.root

    }

    private fun setupRV(responseMovies: List<Movie>) {
        val recyclerView = binding.rvMovies
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        val adapter = MovieAdapter(requireContext(),responseMovies){ movieTitle ->
            onItemClick(movieTitle)
        }
        recyclerView.adapter = adapter
    }

    private fun onItemClick(movieTitle: String) {
        Log.d("MovieAdapter", "Clicked on movie: $movieTitle")
        val action = MoviesFragmentDirections.movieToDetails(movieTitle)
        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesViewModel.sendMovieRequest()
        observerMovies()

    }

    private fun observerMovies() {
        moviesViewModel.observeMoviesLiveData().observe(viewLifecycleOwner
        ) { println("Data is Successfully Coming From VM")
                    setupRV(it)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}