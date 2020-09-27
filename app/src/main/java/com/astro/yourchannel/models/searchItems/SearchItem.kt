package com.astro.yourchannel.models.searchItems


import com.google.gson.annotations.SerializedName

data class SearchItem(
    val etag: String,
    val id: Id,
    val kind: String,
    val snippet: Snippet
)