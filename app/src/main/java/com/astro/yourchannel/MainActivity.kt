package com.astro.yourchannel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.astro.yourchannel.adapters.YtAdapter
import com.astro.yourchannel.repositories.YtRepository
import com.astro.yourchannel.ui.YtViewModel
import com.astro.yourchannel.ui.YtViewModelFactory
import com.astro.yourchannel.util.YtResource
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel : YtViewModel
    lateinit var mAdapter : YtAdapter

    private  val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = YtRepository()
        val viewModelFactory = YtViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(YtViewModel::class.java)

        Log.d(TAG, "onCreate: Settingup recycler view")
        setupRecyclerView()
        Log.d(TAG, "onCreate: Recycler set up completed")
        viewModel.playlistsLiveData.observe(this, Observer {response ->
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
        progressBarMain.visibility = View.INVISIBLE
    }

    private fun showProgressBar(){
        progressBarMain.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        mAdapter = YtAdapter()

        rvMain.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(applicationContext)
        }
    }



}