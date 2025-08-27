package com

import android.app.Application
import com.example.roompractice.di.AppContainer
import com.example.roompractice.di.AppContainerImpl

class JuiceApplication : Application() {
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainerImpl(this)
    }
}