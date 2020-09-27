package com.astro.yourchannel.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.astro.yourchannel.MainActivity
import com.astro.yourchannel.R
import com.astro.yourchannel.adapters.PlaylistItemAdapter
import com.astro.yourchannel.adapters.YtAdapter
import com.astro.yourchannel.ui.YtViewModel
import com.astro.yourchannel.util.YtResource
import kotlinx.android.synthetic.main.fragment_playlist.*
import kotlinx.android.synthetic.main.fragment_video_list.*


class VideoListFragment : Fragment(R.layout.fragment_video_list) {

    val args : VideoListFragmentArgs by navArgs()
    lateinit var viewModel: YtViewModel
    lateinit var mAdapter : PlaylistItemAdapter

    private val TAG = "VideoListFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentPlaylist = args.playlist
        viewModel = (activity as MainActivity).viewModel

        setupRecyclerView()

        viewModel.getPlaylistItems(currentPlaylist.id)
        viewModel.playlistItemLiveData.observe(viewLifecycleOwner, Observer {response ->
            when (response) {
                is YtResource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        mAdapter.differ.submitList(it.playlistItem2s)
                    }
                }

                is YtResource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Log.d(TAG, "onCreate: $it")
                    }
                }

                is YtResource.Loading -> {
                    showProgressBar()
                }
            }
        })


    }
    private fun hideProgressBar(){
        progressBarVideoList.visibility = View.INVISIBLE
    }

    private fun showProgressBar(){
        progressBarVideoList.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        mAdapter = PlaylistItemAdapter()

        rvVideoList.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}