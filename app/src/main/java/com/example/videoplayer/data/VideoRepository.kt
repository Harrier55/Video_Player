package com.example.videoplayer.data

import com.example.videoplayer.R
import com.example.videoplayer.entity.VideoEntity
import com.example.videoplayer.entity.VideoImplementation

class VideoRepository : VideoImplementation {

    private val videoList = mutableListOf<VideoEntity>()

    override fun createVideoEntityList(videoEntity: VideoEntity) {
        videoList.add(videoEntity)
    }

    override fun getListVideo(): List<VideoEntity>{
        return videoList
    }

    fun mockRepo(){
        createVideoEntityList(VideoEntity(1, R.raw.video_1,25))
        createVideoEntityList(VideoEntity(2, R.raw.video_2,25))
        createVideoEntityList(VideoEntity(3, R.raw.video_3,25))
        createVideoEntityList(VideoEntity(4, R.raw.video_4,25))
    }
}