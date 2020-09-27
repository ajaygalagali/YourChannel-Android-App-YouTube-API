package com.astro.yourchannel.models.playlists

import java.io.Serializable


data class PlaylistsItem1(
    val etag: String,
    val id: String,
    val kind: String,
    val snippet: Snippet
):Serializable