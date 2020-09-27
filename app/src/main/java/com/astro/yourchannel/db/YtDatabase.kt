package com.astro.yourchannel.db

import android.content.Context
import androidx.room.*
import com.astro.yourchannel.models.searchItems.Snippet

@Database(
    entities = [Snippet::class] , version = 1
)
@TypeConverters(YtConverts::class)
abstract class YtDatabase : RoomDatabase(){

    abstract fun getSearchItemDAO(): SearchItemDAO

    companion object{

        private var instance: YtDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context)=
            Room.databaseBuilder(context.applicationContext,
            YtDatabase::class.java,
            "yt_db").build()

    }


}