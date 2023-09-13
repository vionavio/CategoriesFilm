package com.viona.categoriesfilm.ui.review

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.viona.categoriesfilm.MyApplication
import com.viona.categoriesfilm.databinding.FragmentReviewBinding
import com.viona.categoriesfilm.ui.review.adapter.ReviewAdapter
import com.viona.categoriesfilm.util.Constants
import com.viona.categoriesfilm.util.ViewModelFactory
import com.viona.categoriesfilm.util.observableData
import javax.inject.Inject


class ReviewFragment : Fragment() {
    private var _binding : FragmentReviewBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: ReviewViewModel by viewModels { factory }

    private lateinit var reviewAdapter: ReviewAdapter

    private val idMovie by lazy { arguments?.getInt(Constants.EXTRA_ID) ?: 0 }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReviewBinding.inflate(inflater, container, false)
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

    private fun initView(){
        reviewAdapter = ReviewAdapter()
        binding.rvReview.adapter = reviewAdapter

    }

    private fun initData() {
        viewModel.getReviewPaging(idMovie).observableData(viewLifecycleOwner) { pagingData ->
            reviewAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
        }
    }
}