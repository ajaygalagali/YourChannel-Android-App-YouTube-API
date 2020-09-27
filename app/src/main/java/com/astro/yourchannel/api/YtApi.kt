package com.astro.yourchannel.api

import com.astro.yourchannel.util.Constants.Companion.API_KEY
import com.astro.yourchannel.util.Constants.Companion.CHANNEL_ID
import com.astro.yourchannel.models.playlistItem.PlaylistItemsResponse
import com.astro.yourchannel.models.playlists.YtPlaylistsResponse
import com.astro.yourchannel.models.searchItems.SearchItemsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


//https://www.youtube.com/channel/UCKNTZMRHPLXfqlbdOI7mCkg
interface YtApi {
    //GET https://www.googleapis.com/youtube/v3/playlists?part=snippet&channelId=UCKNTZMRHPLXfqlbdOI7mCkg&key=[YOUR_API_KEY]

    @GET("v3/playlists")
    suspend fun getPlaylists(
        @Query("part")
        part : String = "snippet",
        @Query("channelId")
        channelId : String = CHANNEL_ID,
        @Query("key")
        api_key : String = API_KEY,
        @Query("maxResults")
        maxResults : Int? = 50
    ):Response<YtPlaylistsResponse>

     /*
    'https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=PLQkwcJG4YTCRF8XiCRESq1IFFW8COlxYJ&key=[YOUR_API_KEY]' \
     */
    @GET("v3/playlistItems")
    suspend fun getPlaylistItems(
         @Query("part")
         part : String = "snippet",
         @Query("playlistId")
         playlistId : String? = null,
         @Query("key")
         api_key: String = API_KEY,
         @Query("maxResults")
         maxResults: Int? = 50
     ):Response<PlaylistItemsResponse>

    @GET("v3/search")
    suspend fun getSearchItems(
        @Query("part")
        part : String = "snippet",
        @Query("key")
        api_key: String = API_KEY,
        @Query("type")
        type : String = "channel",
        @Query("q")
        keyword : String? =null
    ):Response<SearchItemsResponse>


}