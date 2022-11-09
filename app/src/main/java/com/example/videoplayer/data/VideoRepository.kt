package com.example.videoplayer.data

import android.content.Context
import android.util.Log
import com.example.example.VideoData
import com.example.videoplayer.entity.VideoEntity
import com.example.videoplayer.entity.VideoImplementation
import com.google.gson.Gson
import kotlinx.coroutines.*

private const val FOLDER_PATH_ASSETS = "video"
private const val NAME_FILE_JSON = "medialist.json"


class VideoRepository(private val context: Context) : VideoImplementation {

    private val videoList = mutableListOf<VideoEntity>()

    override fun createVideoEntityList(videoEntity: VideoEntity) {
        videoList.add(videoEntity)
    }

    override fun getListVideo(): List<VideoEntity> {

        loadVideoFilesFromAssetsFolder()
        return videoList
    }

    override fun loadVideoFilesFromAssetsFolder() {
        val json = readJson()
        val packageName = readPackageName()
        val videoData = Gson().fromJson(json, Array<VideoData>::class.java)

        videoData.forEach {
            val identifier = it.VideoIdentifier
            val path = "content://$packageName/$FOLDER_PATH_ASSETS/$identifier"
            createVideoEntityList(
                VideoEntity(
                    videoId = it.VideoId!!,
                    videoIdentifier = path,
                    orderNumber = it.OrderNumber!!
                )
            )
        }
    }

    private fun readJson(): String {
        var json: String? = null
        try {
            json = context.assets.open(NAME_FILE_JSON).bufferedReader().use {
                it.readText()
            }
            Log.d("@@@", "readJson: $json")
        } catch (e: Exception) {
            Log.d("@@@", "readJson Error: $e")
        }
        return json!!
    }

    private fun readPackageName(): String {
        return context.packageName
    }

}
