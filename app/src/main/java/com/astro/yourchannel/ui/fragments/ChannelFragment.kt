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
import androidx.recyclerview.widget.LinearLayoutManager
import com.astro.yourchannel.MainActivity
import com.astro.yourchannel.R
import com.astro.yourchannel.adapters.YtAdapter
import com.astro.yourchannel.repositories.YtRepository
import com.astro.yourchannel.ui.YtViewModel
import com.astro.yourchannel.ui.YtViewModelFactory
import com.astro.yourchannel.util.YtResource
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_channel.*

class ChannelFragment : Fragment(R.layout.fragment_channel) {

    lateinit var viewModel : YtViewModel
    lateinit var mAdapter : YtAdapter

    val TAG = "ChannelFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Log.d(TAG, "onCreate: Settingup recycler view")
        viewModel = (activity as MainActivity).viewModel

        setupRecyclerView()

        mAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
//                putSerializable("")
            }
        }

        Log.d(TAG, "onCreate: Recycler set up completed")
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
        progressBarChannel.visibility = View.INVISIBLE
    }

    private fun showProgressBar(){
        progressBarChannel.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        mAdapter = YtAdapter()

        rvChannel.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


}