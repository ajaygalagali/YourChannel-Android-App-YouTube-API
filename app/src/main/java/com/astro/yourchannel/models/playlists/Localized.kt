package com.astro.yourchannel.models.playlists


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Localized(
    val description: String,
    val title: String
):Serializable