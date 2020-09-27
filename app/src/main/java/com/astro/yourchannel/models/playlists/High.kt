package com.astro.yourchannel.models.playlists


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class High(
    val height: Int,
    val url: String,
    val width: Int
):Serializable