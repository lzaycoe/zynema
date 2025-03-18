package io.lzaycoe.zynema.di

import android.content.Context
import androidx.room.Room
import io.lzaycoe.zynema.data.datasource.local.MovieDatabase
import io.lzaycoe.zynema.data.datasource.local.dao.FavoriteMovieDao
import io.lzaycoe.zynema.data.datasource.local.dao.FavoriteTvSeriesDao
import io.lzaycoe.zynema.data.repository.local.movie.LocalMovieRepository
import io.lzaycoe.zynema.data.repository.local.tvseries.LocalTvSeriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "movieWorld.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideMovieDetailDao(moviesDatabase: MovieDatabase): FavoriteMovieDao =
        moviesDatabase.getFavoriteMovieDetailDao()

    @Singleton
    @Provides
    fun provideLocalMovieRepo(movieDao: FavoriteMovieDao): LocalMovieRepository =
        LocalMovieRepository(movieDao)

    @Singleton
    @Provides
    fun provideTvSeriesDao(moviesDatabase: MovieDatabase): FavoriteTvSeriesDao =
        moviesDatabase.getFavoriteTvSeriesDao()

    @Singleton
    @Provides
    fun provideLocalTvSeriesRepo(tvSeriesDao: FavoriteTvSeriesDao): LocalTvSeriesRepository =
        LocalTvSeriesRepository(tvSeriesDao)
}