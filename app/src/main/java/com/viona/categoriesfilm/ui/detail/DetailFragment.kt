package com.viona.categoriesfilm.ui.detail

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.viona.categoriesfilm.MyApplication
import com.viona.categoriesfilm.R
import com.viona.categoriesfilm.core.domain.model.Movie
import com.viona.categoriesfilm.core.domain.model.VideoStream
import com.viona.categoriesfilm.databinding.FragmentDetailBinding
import com.viona.categoriesfilm.ui.detail.adapter.ReviewAdapter
import com.viona.categoriesfilm.ui.detail.adapter.ThumbnailAdapter
import com.viona.categoriesfilm.util.Constants
import com.viona.categoriesfilm.util.Constants.MAX_RATING
import com.viona.categoriesfilm.util.ViewModelFactory
import com.viona.categoriesfilm.util.genresText
import com.viona.categoriesfilm.util.loadWithGlide
import com.viona.categoriesfilm.util.movieLanguage
import com.viona.categoriesfilm.util.movieEpisode
import com.viona.categoriesfilm.util.observableData
import com.viona.categoriesfilm.util.setUpToolbar
import javax.inject.Inject

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: DetailViewModel by viewModels { factory }

    private val idMovie by lazy { arguments?.getInt(Constants.EXTRA_ID) ?: 0 }
    private val titleMovie by lazy { arguments?.getString(Constants.EXTRA_TITLE).orEmpty() }

    private val videoAdapter = ThumbnailAdapter()
    private val reviewAdapter = ReviewAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.youtubePlayerView.release()
        _binding = null
    }

    private fun initData(){
        viewModel.getDetailMovie(idMovie)
        viewModel.getVideoMovie(idMovie.toLong())
        viewModel.getReviewMovie(idMovie)
        viewModel.dataMovie.observableData(this) { result ->
            if (result != null) setupDataMovie(result)
        }
        viewModel.videoMovie.observableData(this) { result ->
            if (result != null) {
                binding.apply {
                    youtubePlayerView.visibility = View.VISIBLE
                    ivBackdrop.visibility = View.GONE
                    fadeView.visibility = View.GONE
                    setupYoutubePlayer(result)

                }
            }
        }
        viewModel.videoMovieList.observableData(this) { result ->
            videoAdapter.submitData(result)
        }
        viewModel.reviewMovie.observableData(this) { result ->
            if (result.isNotEmpty()) {reviewAdapter.submitData(result)}
            else {
                binding.llReview.visibility = View.GONE
            }
        }
        viewModel.errorMessage.observableData(this) { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
    private fun initView() = with(binding){
        toolbar.setUpToolbar(activity as AppCompatActivity, titleMovie, tvToolbar)
        rvVideo.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = videoAdapter
        }
        rvReview.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = reviewAdapter
        }
    }

    @SuppressLint("StringFormatMatches")
    private fun setupDataMovie(movie: Movie) = with(binding){
        ivBackdrop.loadWithGlide(Constants.getBackdropUrl(movie.backdropPath.orEmpty()))
        titleText.text = movie.title
        genresText.genresText(movie.genres)
        ratingBar.rating = 5 * ((movie.voteAverage / MAX_RATING))
        numOfVotes.text = getString(R.string.votes_count, movie.voteCount )
        episodeText.movieEpisode(movie.releaseDate.orEmpty())
        seasonText.movieEpisode(movie.runtime)
        airDateText.movieLanguage(movie.originalLanguage)
        overviewText.text = movie.overview
        tvShowAll.setOnClickListener {
            val bundle = Bundle().apply {
                putInt(Constants.EXTRA_ID, movie.id)
                putString(Constants.EXTRA_TITLE, movie.title)
            }
            it.findNavController().navigate(R.id.action_detailFragment_to_reviewFragment, bundle)
        }
    }

    private fun setupYoutubePlayer(result: VideoStream){
        binding.apply {
            youtubePlayerView.addYouTubePlayerListener(
                object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(result.key, 0f)
                    }
                }
            )
        }
    }
}