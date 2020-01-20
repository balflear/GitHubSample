package com.github.example.presentation.di

import com.github.example.data.repository.GithubRepositoryImpl
import com.github.example.data.service.GithubService
import com.github.example.domain.repository.GithubRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by kostadin.georgiev on 1/7/2020.
 */
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideGithubRepository(githubService: GithubService): GithubRepository =
        GithubRepositoryImpl(githubService)
}