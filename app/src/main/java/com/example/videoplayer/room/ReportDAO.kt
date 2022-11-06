package com.example.videoplayer.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ReportDAO {

    @Query("SELECT * FROM ReportEntity")
    fun getAll(): List<ReportEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: ReportEntity)

}