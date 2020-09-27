package com.astro.yourchannel.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.astro.yourchannel.models.searchItems.Snippet

@Dao
interface SearchItemDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item : Snippet)

    @Delete
    suspend fun delete(item: Snippet)

    @Query("SELECT * FROM tbl_search_snippet")
    fun getAllChannels() : LiveData<List<Snippet>>
}