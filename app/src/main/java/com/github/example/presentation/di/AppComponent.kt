package com.github.example.presentation.di

import com.github.example.presentation.RepositoriesActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by kostadin.georgiev on 1/6/2020.
 */

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, RepositoryModule::class, ViewModelsModule::class])
interface AppComponent {

    fun inject(repositoriesActivity: RepositoriesActivity)

}