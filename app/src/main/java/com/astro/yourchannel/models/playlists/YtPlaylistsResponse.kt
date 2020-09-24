package com.astro.yourchannel.models.playlists

import com.google.gson.annotations.SerializedName


data class YtPlaylistsResponse(
    val etag: String,
    @SerializedName("items")
    val playlistsItem1s: List<PlaylistsItem1>,
    val kind: String,
    val pageInfo: PageInfo
)