package com.kost4n.yourmood.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRec(rec: Rec)

    @Query("SELECT * FROM recs ORDER BY id DESC")
    fun getRecs(): LiveData<List<Rec>>
}