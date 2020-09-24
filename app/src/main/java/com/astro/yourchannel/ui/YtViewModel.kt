package com.astro.yourchannel.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.astro.yourchannel.models.playlists.YtPlaylistsResponse
import com.astro.yourchannel.repositories.YtRepository
import com.astro.yourchannel.util.YtResource
import kotlinx.coroutines.launch
import retrofit2.Response

class YtViewModel(
    val repository: YtRepository
) : ViewModel(){

    val playlistsLiveData : MutableLiveData<YtResource<YtPlaylistsResponse>> = MutableLiveData()

    fun getPlaylists() = viewModelScope.launch {
        playlistsLiveData.postValue(YtResource.Loading())
        val response  = repository.getPlaylists()
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