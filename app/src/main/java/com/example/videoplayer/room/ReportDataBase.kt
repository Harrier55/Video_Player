package com.example.videoplayer.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ReportEntity::class],
    version = 1,
    exportSchema = false
)

abstract class ReportDataBase : RoomDatabase() {
    abstract fun getReportDao(): ReportDAO
}