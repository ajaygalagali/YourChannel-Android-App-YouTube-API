
package com.astro.yourchannel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.astro.yourchannel.adapters.PlaylistItemAdapter
import com.astro.yourchannel.adapters.YtAdapter
import com.astro.yourchannel.repositories.YtRepository
import com.astro.yourchannel.ui.YtViewModel
import com.astro.yourchannel.ui.YtViewModelFactory
import com.astro.yourchannel.util.YtResource
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_playlist_item.*

class PlaylistItemActivity : AppCompatActivity() {

    private val TAG = "playlistItemActivity"

    lateinit var viewModel : YtViewModel
    lateinit var mAdapterItems : PlaylistItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist_item)

        val playListId = intent.getStringExtra("playlistId")
        val playlistTitle = intent.getStringExtra("playlistTitle")

        toolbarItem.title = playlistTitle
        toolbarItem.setNavigationOnClickListener {
            finish()
        }

        val repository = YtRepository()
        val viewModelFactory = YtViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(YtViewModel::class.java)

        viewModel.getPlaylistItems(playListId)

        setupRecyclerView()
        viewModel.playlistItemLiveData.observe(this, Observer {response ->
            when (response) {
                is YtResource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        mAdapterItems.differ.submitList(it.playlistItem2s)
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
        progressBarItems.visibility = View.INVISIBLE
    }

    private fun showProgressBar(){
        progressBarItems.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        mAdapterItems = PlaylistItemAdapter(this)

        rvItems.apply {
            adapter = mAdapterItems
            layoutManager = LinearLayoutManager(applicationContext)
        }
    }
}