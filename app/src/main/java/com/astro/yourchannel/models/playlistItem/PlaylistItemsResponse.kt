package com.astro.yourchannel.models.playlistItem


import com.google.gson.annotations.SerializedName

data class PlaylistItemsResponse(
    val etag: String,
    val items: List<Item>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfo
)