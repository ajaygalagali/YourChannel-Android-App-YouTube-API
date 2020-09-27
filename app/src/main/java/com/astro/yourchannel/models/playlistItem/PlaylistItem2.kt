package com.astro.yourchannel.models.playlistItem


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PlaylistItem2(
    val etag: String,
    val id: String,
    val kind: String,
    val snippet: Snippet
):Serializable