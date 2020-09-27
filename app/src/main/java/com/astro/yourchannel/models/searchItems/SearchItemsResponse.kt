package com.astro.yourchannel.models.searchItems

import com.google.gson.annotations.SerializedName


data class SearchItemsResponse(
    val etag: String,
    @SerializedName("items")
    val searchItems: List<SearchItem>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfo,
    val regionCode: String
)