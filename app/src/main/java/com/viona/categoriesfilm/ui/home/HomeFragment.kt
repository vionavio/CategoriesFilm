package com.viona.categoriesfilm.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.viona.categoriesfilm.MyApplication
import com.viona.categoriesfilm.R
import com.viona.categoriesfilm.core.domain.model.type.MovieType
import com.viona.categoriesfilm.databinding.FragmentHomeBinding
import com.viona.categoriesfilm.ui.home.adapter.MovieAdapter
import com.viona.categoriesfilm.util.Constants.EXTRA_TYPE
import com.viona.categoriesfilm.util.ViewModelFactory
import com.viona.categoriesfilm.util.observableData
import javax.inject.Inject


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: HomeViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initData() {
        viewModel.getPopularMovie()
        viewModel.getUpcomingMovie()
        viewModel.getTheatreMovie()

        val popularMovieAdapter = MovieAdapter()
        val upcomingMovieAdapter = MovieAdapter()
        val theatreMovieAdapter = MovieAdapter()
        binding.rvPopular.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularMovieAdapter
        }
        binding.rvUpcoming.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = upcomingMovieAdapter
        }
        binding.rvTheatre.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = theatreMovieAdapter
        }
        viewModel.popularMovie.observableData(this) { result ->
            popularMovieAdapter.submitData(result)
        }
        viewModel.upcomingMovie.observableData(this) { result ->
            upcomingMovieAdapter.submitData(result)
        }
        viewModel.theatreMovie.observableData(this) { result ->
            theatreMovieAdapter.submitData(result)
        }
    }

    private fun initView() = with(binding) {

        tvShowPopular.setOnClickListener { goToMovie(it, MovieType.POPULAR.name) }
        tvShowAllTheatre.setOnClickListener { goToMovie(it, MovieType.THEATRE.name) }
        tvShowAllUpcoming.setOnClickListener { goToMovie(it, MovieType.UPCOMING.name) }

    }

    private fun goToMovie(view: View, type: String) {
        val bundle = Bundle().apply {
            putString(EXTRA_TYPE, type)
        }
        view.findNavController().navigate(R.id.action_homeFragment_to_moviesFragment, bundle)
    }
}