package com.astro.yourchannel.models.playlistItem


import com.google.gson.annotations.SerializedName

data class PageInfo(
    val resultsPerPage: Int,
    val totalResults: Int
)