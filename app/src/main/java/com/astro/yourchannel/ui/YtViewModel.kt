package com.astro.yourchannel.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.astro.yourchannel.models.playlists.YtPlaylistsResponse
import com.astro.yourchannel.repositories.YtRepository
import com.astro.yourchannel.util.YtResource
import kotlinx.coroutines.launch
import retrofit2.Response

class YtViewModel(
    private val repository: YtRepository
) : ViewModel(){
    private val TAG = "YtViewModel"
    
    val playlistsLiveData : MutableLiveData<YtResource<YtPlaylistsResponse>> = MutableLiveData()

    init {
        getPlaylists()
    }

    fun getPlaylists() = viewModelScope.launch {
        playlistsLiveData.postValue(YtResource.Loading())
        val response  = repository.getPlaylists()
        Log.d(TAG, "getPlaylists: ${response.body()}")
        playlistsLiveData.postValue(handlePlaylistsResponse(response))
    }

    private fun handlePlaylistsResponse(response : Response<YtPlaylistsResponse>) : YtResource<YtPlaylistsResponse>{
        if(response.isSuccessful){
            response.body()?.let {
                return YtResource.Success(it)
            }
        }

        return YtResource.Error(message = response.message())
    }

}