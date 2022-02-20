package com.bulgakov.bestmovie.di

import com.bulgakov.bestmovie.api.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideMovieService(): MovieService {
        return MovieService.create()
    }
}