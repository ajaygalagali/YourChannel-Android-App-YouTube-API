package com.astro.yourchannel.models.playlists


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Medium(
    val height: Int,
    val url: String,
    val width: Int
):Serializable