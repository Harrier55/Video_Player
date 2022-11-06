package com.example.videoplayer

import android.app.Application
import com.example.videoplayer.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(
                AppModule.mainModule,
                AppModule.dataBaseModule
            )

        }
    }
}