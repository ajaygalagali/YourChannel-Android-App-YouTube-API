package com.astro.yourchannel.repositories

import com.astro.yourchannel.api.RetrofitInstance

class YtRepository {

    suspend fun getPlaylists() =
        RetrofitInstance.api.getPlaylists()


    suspend fun getPlaylistItems(playlistId : String) =
        RetrofitInstance.api.getPlaylistItems(playlistId = playlistId)

    suspend fun getSearchItems(keyword : String) =
        RetrofitInstance.api.getSearchItems(keyword = keyword)

}