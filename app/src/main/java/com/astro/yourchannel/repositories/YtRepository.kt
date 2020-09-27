package com.astro.yourchannel.repositories

import com.astro.yourchannel.api.RetrofitInstance
import com.astro.yourchannel.db.YtDatabase
import com.astro.yourchannel.models.searchItems.Snippet

class YtRepository(
    val db : YtDatabase
) {

    suspend fun getPlaylists(channelId : String) =
        RetrofitInstance.api.getPlaylists(channelId = channelId)


    suspend fun getPlaylistItems(playlistId : String) =
        RetrofitInstance.api.getPlaylistItems(playlistId = playlistId)

    suspend fun getSearchItems(keyword : String) =
        RetrofitInstance.api.getSearchItems(keyword = keyword)

    suspend fun upsert(item : Snippet) = db.getSearchItemDAO().upsert(item)

    suspend fun delete(item : Snippet) = db.getSearchItemDAO().delete(item)

    fun getAllChannels() = db.getSearchItemDAO().getAllChannels()
}