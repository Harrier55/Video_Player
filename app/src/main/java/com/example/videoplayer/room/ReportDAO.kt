package com.example.videoplayer.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ReportDAO {

    @Query("SELECT * FROM ReportEntity")
    suspend fun getAll(): List<ReportEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: ReportEntity)

}