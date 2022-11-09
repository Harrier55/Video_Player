package com.example.videoplayer.entity

interface VideoImplementation {
    fun createVideoEntityList(videoEntity: VideoEntity)
    fun getListVideo(): List<VideoEntity>
    fun loadVideoFilesFromAssetsFolder()
}
