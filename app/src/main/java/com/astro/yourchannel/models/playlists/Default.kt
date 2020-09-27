package com.astro.yourchannel.models.playlists


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Default(
    val height: Int,
    val url: String,
    val width: Int
):Serializable