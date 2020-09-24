package com.astro.yourchannel.models.playlistItem


import com.google.gson.annotations.SerializedName

data class Item(
    val etag: String,
    val id: String,
    val kind: String,
    val snippet: Snippet
)