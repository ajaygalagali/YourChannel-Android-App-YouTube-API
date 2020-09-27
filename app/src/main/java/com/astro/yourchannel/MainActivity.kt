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
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
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

        bottomNavigationView.setupWithNavController(navHostFragment.findNavController())

        val pref = getSharedPreferences("yt_pref", AppCompatActivity.MODE_PRIVATE)
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

    }





}