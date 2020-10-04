package com.astro.yourchannel.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.astro.yourchannel.MainActivity
import com.astro.yourchannel.R
import com.astro.yourchannel.adapters.ChannelListAdapter
import com.astro.yourchannel.adapters.SearchChannelAdapter
import com.astro.yourchannel.adapters.YtAdapter
import com.astro.yourchannel.repositories.YtRepository
import com.astro.yourchannel.ui.YtViewModel
import com.astro.yourchannel.ui.YtViewModelFactory
import com.astro.yourchannel.util.YtResource
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_channel.*
import kotlinx.android.synthetic.main.fragment_playlist.*

class ChannelFragment : Fragment(R.layout.fragment_channel) {

    lateinit var viewModel: YtViewModel
    lateinit var mAdapter: ChannelListAdapter

    val TAG = "ChannelFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()
        requireActivity().toolbarMain.title = getString(R.string.app_name)

        viewModel.getAllchannels().observe(viewLifecycleOwner, Observer {
            mAdapter.differ.submitList(it)
        })

        mAdapter.setOnItemClickListener {
            viewModel.deleteChannel(it)
        }

        mAdapter.setOnCardViewListener {
            val bundle = Bundle().apply {
                putSerializable("channel",it)
            }

            findNavController().navigate(R.id.action_channelFragment_to_playlistFragment, bundle)
        }

    }

    private fun setupRecyclerView(){
        mAdapter = ChannelListAdapter()

        rvChannel.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}