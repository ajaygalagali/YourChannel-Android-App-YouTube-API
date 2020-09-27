package com.astro.yourchannel.db

import androidx.room.TypeConverter
import com.astro.yourchannel.models.searchItems.Default
import com.astro.yourchannel.models.searchItems.High
import com.astro.yourchannel.models.searchItems.Medium
import com.astro.yourchannel.models.searchItems.Thumbnails

class YtConverts {

    @TypeConverter
    fun fromThumbnail(thumbnails: Thumbnails):String{
        return thumbnails.default.url
    }

    @TypeConverter
    fun toThumbnail(url : String) : Thumbnails{
        return  Thumbnails(Default(url), High(url), Medium(url))
    }

}