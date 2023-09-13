package com.viona.categoriesfilm.ui.list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.viona.categoriesfilm.MyApplication
import com.viona.categoriesfilm.core.domain.model.type.MovieType
import com.viona.categoriesfilm.databinding.FragmentMoviesBinding
import com.viona.categoriesfilm.ui.list.adapter.MovieListAdapter
import com.viona.categoriesfilm.util.Constants
import com.viona.categoriesfilm.util.ViewModelFactory
import com.viona.categoriesfilm.util.observableData
import com.viona.categoriesfilm.util.setUpToolbar
import javax.inject.Inject


class MoviesFragment : Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val type by lazy { arguments?.getString(Constants.EXTRA_TYPE).orEmpty() }

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: MoviesViewModel by viewModels { factory }

    private lateinit var movieAdapter: MovieListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
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

    private fun initView() {
        movieAdapter = MovieListAdapter()
        binding.apply {
            rvMovie.adapter = movieAdapter
            toolbar.setUpToolbar(activity as AppCompatActivity, type, tvToolbar)
        }

    }

    private fun initData() {
        val typeMovie = MovieType.getTypeByString(type)
        viewModel.getMoviePaging(typeMovie).observableData(viewLifecycleOwner) { pagingData ->
            movieAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
        }
    }

}