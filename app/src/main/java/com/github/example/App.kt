package com.github.example

import android.app.Application
import com.github.example.presentation.di.AppComponent
import com.github.example.presentation.di.AppModule
import com.github.example.presentation.di.DaggerAppComponent

/**
 * Created by kostadin.georgiev on 1/6/2020.
 */
class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = initDagger(this)
    }

    private fun initDagger(app: App): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()
}