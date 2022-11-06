package com.example.videoplayer.di

import com.example.videoplayer.data.VideoRepository
import com.example.videoplayer.ui.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    val mainModule = module {
        single { VideoRepository() }

        viewModel{MainActivityViewModel(videoRepository = get())}
    }
}