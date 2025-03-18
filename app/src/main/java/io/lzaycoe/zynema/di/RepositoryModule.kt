package io.lzaycoe.zynema.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.lzaycoe.zynema.data.datasource.remote.ApiService
import io.lzaycoe.zynema.data.repository.remote.movie.MovieRepository
import io.lzaycoe.zynema.data.repository.remote.tvseries.TvSeriesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
  /** Provides RemoteDataRepository for access api service method */
  @Singleton
  @Provides
  fun provideMovieRepository(
      apiService: ApiService,
  ): MovieRepository {
    return MovieRepository(apiService)
  }

  /** Provides RemoteDataRepository for access api service method */
  @Singleton
  @Provides
  fun provideTvSeriesRepository(
      apiService: ApiService,
  ): TvSeriesRepository {
    return TvSeriesRepository(apiService)
  }
}
