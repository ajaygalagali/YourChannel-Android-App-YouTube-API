package com.astro.yourchannel.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.astro.yourchannel.repositories.YtRepository

class YtViewModelFactory(
    val repository: YtRepository
) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return YtViewModel(repository) as T
    }

}