package com.example.myyoutubever2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myyoutubever2.R
import com.example.myyoutubever2.adapter.VideoRecommendAdapter
import com.example.myyoutubever2.databinding.VideoRecommendFragmentBinding
import com.example.myyoutubever2.viewmodel.PlayerFragViewModel

class VideoRecommendFragment : Fragment() {

    private lateinit var viewModel: PlayerFragViewModel
    private lateinit var binding : VideoRecommendFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.video_recommend_fragment,
            container,
            false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(parentFragment!!).get(PlayerFragViewModel::class.java)
        binding.viewModel = viewModel

        binding.listVideoRecommend.layoutManager = LinearLayoutManager(context)
        binding.listVideoRecommend.adapter = VideoRecommendAdapter()

        viewModel.playVideo.observe(viewLifecycleOwner, {
            val adapter = binding.listVideoRecommend.adapter as VideoRecommendAdapter
            adapter.setPlayVideo(it)
        })

        viewModel.recommendVideoData.observe(viewLifecycleOwner, {
            val adapter = binding.listVideoRecommend.adapter as VideoRecommendAdapter
            adapter.setRecommendVideoList(it)
        })
    }

    companion object {
        fun newInstance() = VideoRecommendFragment()
    }

}