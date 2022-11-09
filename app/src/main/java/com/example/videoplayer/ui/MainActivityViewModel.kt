package com.example.videoplayer.ui

import android.os.ParcelFileDescriptor.open
import android.system.Os.open
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videoplayer.data.VideoRepository
import com.example.videoplayer.entity.VideoEntity
import com.example.videoplayer.room.ReportDAO
import com.example.videoplayer.room.ReportEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.nio.channels.FileChannel.open
import java.util.*

class MainActivityViewModel(
    private val videoRepository: VideoRepository,
    private val reportDAO: ReportDAO
) : ViewModel() {

    private val list by lazy { videoRepository.getListVideo() }
    private val listSize = list.size
    private var index = 0

    val videoContent = MutableLiveData<VideoEntity>()
    val reportDataBaseList = MutableLiveData<List<ReportEntity>>()

    fun startVideo() {
        if (index != listSize - 1) {
            index++
        } else if (index == listSize - 1) {
            index = 0
        }
        val videoEntity = list[index]
        videoContent.postValue(videoEntity)
        writeToDataBase(videoEntity)
    }

    private fun writeToDataBase(videoEntity: VideoEntity) {
        val time = Calendar.getInstance().time.toString()
        viewModelScope.launch {
            try {
                reportDAO.insert(
                    ReportEntity(
                        0,
                        id_video = videoEntity.videoId,
                        video_name = "Имя не определено",
                        start_time = time
                    )
                )
            } catch (e: Exception) {
                println("MainActivityViewModel_writeToDataBase_Error :$e")
            }
        }
    }

    fun readDataBase() {
        viewModelScope.launch {
            try {
                val list = reportDAO.getAll()
                reportDataBaseList.postValue(list)
            } catch (e: Exception) {
                println("MainActivityViewModel_readDataBase_Error :$e")
            }
        }
    }


}