package com.example.searchgithubrepositories.di

import com.example.searchgithubrepositories.api.GithubService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGithubService(): GithubService = GithubService.create()
}
