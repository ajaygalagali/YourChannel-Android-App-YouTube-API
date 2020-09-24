package com.astro.yourchannel.models.playlistItem

import com.google.gson.annotations.SerializedName


data class PlaylistItemsResponse(
    val etag: String,
    @SerializedName("items")
    val playlistItem2s: List<PlaylistItem2>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfo
)