package com.example.videoplayer.di

import androidx.room.Room
import com.example.videoplayer.data.VideoRepository
import com.example.videoplayer.room.ReportDataBase
import com.example.videoplayer.ui.MainActivityViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val DB_NAME = "minitv.db"

object AppModule {

    val mainModule = module {
        single { VideoRepository() }

        viewModel {
            MainActivityViewModel(
                videoRepository = get(),
                dataBase = get()
            )
        }
    }

    val dataBaseModule = module {
        single {
            Room.databaseBuilder(
                androidContext(),
                ReportDataBase::class.java,
                DB_NAME
            ).build()
        }

        single {
            val dataBase = get<ReportDataBase>()
            dataBase.getReportDao()
        }

    }
}