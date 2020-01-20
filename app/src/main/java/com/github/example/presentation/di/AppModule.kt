package com.github.example.presentation.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by kostadin.georgiev on 1/6/2020.
 */

@Module
class AppModule(private val appContext: Context) {
    @Provides
    @Singleton
    fun provideContext(): Context = appContext
}