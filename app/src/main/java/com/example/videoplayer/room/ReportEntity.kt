package com.example.videoplayer.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

private const val NOTE_COLUMN_ID_VIDEO = "id_video"
private const val NOTE_COLUMN_NAME_VIDEO = "video_name"
private const val NOTE_COLUMN_START_TIME = "start_time"

@Entity
data class ReportEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = NOTE_COLUMN_ID_VIDEO)
    val id_video:Int,
    @ColumnInfo(name = NOTE_COLUMN_NAME_VIDEO)
    val video_name:String,
    @ColumnInfo(name = NOTE_COLUMN_START_TIME)
    val start_time: String
)
