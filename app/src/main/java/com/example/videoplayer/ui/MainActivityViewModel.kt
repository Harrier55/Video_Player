package com.example.videoplayer.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.videoplayer.data.VideoRepository
import com.example.videoplayer.entity.VideoEntity

class MainActivityViewModel(private val videoRepository: VideoRepository) : ViewModel() {

    init {
        videoRepository.mockRepo()
    }

    private val list by lazy { videoRepository.getListVideo() }
    private val listSize = list.size
    private var index = 0

    val videoContent = MutableLiveData<VideoEntity>()

    fun startVideo() {

        if (index != listSize-1) {
            index++
        } else if (index == listSize-1) {
            index = 0
        }
        videoContent.postValue(list[index])
    }


}