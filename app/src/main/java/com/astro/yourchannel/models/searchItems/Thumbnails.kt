package com.astro.yourchannel.models.searchItems


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Thumbnails(
    val default: Default,
    val high: High,
    val medium: Medium
):Serializable