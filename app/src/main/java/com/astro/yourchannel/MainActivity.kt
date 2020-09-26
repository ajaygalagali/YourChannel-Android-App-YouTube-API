package com.astro.yourchannel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import androidx.core.view.get
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

        val pref = getSharedPreferences("yt_pref", MODE_PRIVATE)
        val editor = pref.edit()

        var isLightt = pref.getBoolean("isLight",true)

        if(isLightt){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        
        toolbarMain.setOnMenuItemClickListener {

            when(it.title){
                "Dark/Light" -> {
                    var isLight = pref.getBoolean("isLight",true)

                    if(isLight){
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        editor.putBoolean("isLight",false)
                        editor.apply()
                    }else{
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        editor.putBoolean("isLight",true)
                        editor.apply()
                    }
                }



            }

            return@setOnMenuItemClickListener true
        }


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
        mAdapter = YtAdapter(this)

        rvMain.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(applicationContext)
        }
    }



}