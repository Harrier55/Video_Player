package com.example.videoplayer.ui

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.videoplayer.databinding.ActivityMainBinding
import com.example.videoplayer.entity.VideoEntity
import org.koin.android.ext.android.inject
import java.io.IOException


private const val TAG = "@@@"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var surfaceViewHolder: SurfaceHolder
    private var mediaPlayer: MediaPlayer = MediaPlayer()
    private val viewModel: MainActivityViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestRuntimePermission()
        initViewHolder()
        initBottomClick()
        viewModel.startVideo()

        viewModel.videoContent.observe(this) { videoEntity ->
            playerStart(videoEntity)
        }

        viewModel.reportDataBaseList.observe(this){
            binding.listDBTextView.text = it.toString()
        }
    }

    private fun playerStart(videoEntity: VideoEntity) {

        val videoId = videoEntity.videoIdentifier

        try {
            mediaPlayer = MediaPlayer.create(this, videoId)  // это работает только  с ресурсом
        } catch (e: IOException) {
            Log.d(TAG, "Error: $e")
        }

        mediaPlayer.setOnPreparedListener {
            Log.d(TAG, "Готов:")
            mediaPlayer.setDisplay(surfaceViewHolder)
            mediaPlayer.start()
        }

        mediaPlayer.setOnCompletionListener {
            Log.d(TAG, "Complete:")
            mediaPlayer.reset()
            viewModel.startVideo()
        }
    }

    private fun initBottomClick(){
        binding.buttonStart.setOnClickListener {
            mediaPlayer.start()
        }

        binding.buttonStop.setOnClickListener {
            mediaPlayer.stop()
        }

        binding.buttonDB.setOnClickListener {
            viewModel.readDataBase()
        }
    }

    private fun initViewHolder() {
        surfaceViewHolder = binding.surfaceView.holder

        surfaceViewHolder.addCallback(object : SurfaceHolder.Callback {

            override fun surfaceCreated(p0: SurfaceHolder) {

                Log.d(TAG, "surfaceCreated: ")
            }

            override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
                Log.d(TAG, "surfaceChanged: ")
            }

            override fun surfaceDestroyed(p0: SurfaceHolder) {
                Log.d(TAG, "surfaceDestroyed: ")
            }
        })
    }

    private fun requestRuntimePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1
                )
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.release()
    }
}
