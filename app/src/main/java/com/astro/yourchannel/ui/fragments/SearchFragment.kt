package com.astro.yourchannel.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.astro.yourchannel.MainActivity
import com.astro.yourchannel.R
import com.astro.yourchannel.adapters.SearchChannelAdapter
import com.astro.yourchannel.ui.YtViewModel
import com.astro.yourchannel.util.YtResource
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment : Fragment(R.layout.fragment_search) {
    lateinit var viewModel : YtViewModel
    lateinit var mAdapterSearch : SearchChannelAdapter

    private val TAG = "SearchFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        setUpRecyclerView()

        var job : Job? = null

        etSearch.addTextChangedListener { keyword->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                keyword?.let {
                    if(keyword.toString().isNotEmpty()) {

                        viewModel.getSearchItems(keyword = keyword.toString())

                    }
                }
            }

        }

        viewModel.searchItemLiveData.observe(viewLifecycleOwner, Observer {

            when (it) {

                is YtResource.Success -> {
                    hideProgressBar()
                    it.data?.let { res ->
                        mAdapterSearch.differ.submitList(res.searchItems)
                    }
                }

                is YtResource.Error -> {
                    hideProgressBar()
                    it.message?.let { msg->
                        Log.e(TAG, "onViewCreated: $msg", )
                    }
                }

                is YtResource.Loading -> {
                    showProgressBar()
                }

            }

        })



    }

    private fun hideProgressBar(){
        progressBarSearch.visibility = View.INVISIBLE
    }

    private fun showProgressBar(){
        progressBarSearch.visibility = View.VISIBLE
    }

    private fun setUpRecyclerView(){

        mAdapterSearch = SearchChannelAdapter()
        rvSearch.apply {
            adapter = mAdapterSearch
            layoutManager = LinearLayoutManager(activity)
        }

    }


}