package com.astro.yourchannel.models.playlistItem


import com.google.gson.annotations.SerializedName

data class ResourceId(
    val kind: String,
    val videoId: String
)