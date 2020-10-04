package com.astro.yourchannel.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.astro.yourchannel.MainActivity
import com.astro.yourchannel.R
import com.astro.yourchannel.adapters.SearchChannelAdapter
import com.astro.yourchannel.adapters.YtAdapter
import com.astro.yourchannel.ui.YtViewModel
import com.astro.yourchannel.util.YtResource
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_channel.*
import kotlinx.android.synthetic.main.fragment_playlist.*


class PlaylistFragment : Fragment(R.layout.fragment_playlist) {

    lateinit var viewModel : YtViewModel
    lateinit var mAdapter : YtAdapter
    private val TAG = "PlaylistFragment"

    private val args : PlaylistFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel



        setupRecyclerView()
        mAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("playlist",it)
            }

            findNavController().navigate(R.id.action_playlistFragment_to_videoListFragment,bundle)
        }
        val currentChannel = args.channel
        requireActivity().toolbarMain.title = currentChannel.channelTitle


        viewModel.getPlaylists(currentChannel.channelId)
        viewModel.playlistsLiveData.observe(viewLifecycleOwner, Observer {response ->
            when (response) {
                is YtResource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        mAdapter.differ.submitList(it.playlistsItem1s)
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
        progressBarPlaylist.visibility = View.INVISIBLE
    }

    private fun showProgressBar(){
        progressBarPlaylist.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        mAdapter = YtAdapter()

        rvPlaylist.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }



}