package com.astro.yourchannel.models.playlists

import java.io.Serializable


data class Thumbnails(
    val default: Default,
    val high: High,
    val maxres: Maxres,
    val medium: Medium,
    val standard: Standard
):Serializable