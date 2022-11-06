package com.example.videoplayer.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class ReportEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val id_video:Int,
    val video_name:String,
    val start_time: Date
)
