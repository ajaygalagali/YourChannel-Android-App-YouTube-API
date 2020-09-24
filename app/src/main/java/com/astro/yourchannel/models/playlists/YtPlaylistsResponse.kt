package com.astro.yourchannel.models.playlists


data class YtPlaylistsResponse(
    val etag: String,
    val items: List<Item>,
    val kind: String,
    val pageInfo: PageInfo
)