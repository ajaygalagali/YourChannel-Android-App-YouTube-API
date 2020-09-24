package com.astro.yourchannel.models.playlistItem


import com.google.gson.annotations.SerializedName

data class Thumbnails(
    val default: Default,
    val high: High,
    val maxres: Maxres,
    val medium: Medium,
    val standard: Standard
)