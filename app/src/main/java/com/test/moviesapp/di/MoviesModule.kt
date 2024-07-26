package com.test.moviesapp.di

import com.test.moviesapp.data.remote.IMoviesService
import com.test.moviesapp.data.remote.MoviesService
import com.test.moviesapp.data.remote.MoviesServiceImpl
import com.test.moviesapp.data.repository.IMoviesRepository
import com.test.moviesapp.data.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MoviesModule {

    //Remote
    @Singleton
    @Binds
    abstract fun provideMovieServices(
        moviesServiceImpl: MoviesServiceImpl
    ): IMoviesService

    @Singleton
    @Binds
    abstract fun provideMoviesRepository(
        moviesRepositoryImpl: MoviesRepository
    ): IMoviesRepository


}


@Module
@InstallIn(SingletonComponent::class)
object MoviesModuleObj {

    @Singleton
    @Provides
    fun provideMoviesService(
        retrofit: Retrofit
    ): MoviesService {
        return retrofit.create(MoviesService::class.java)
    }
}

