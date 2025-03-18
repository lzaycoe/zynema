package io.lzaycoe.zynema.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.lzaycoe.zynema.data.datasource.local.dao.FavoriteMovieDao
import io.lzaycoe.zynema.data.datasource.local.dao.FavoriteTvSeriesDao
import io.lzaycoe.zynema.data.datasource.local.typeconverter.MovieTypeConverter
import io.lzaycoe.zynema.data.datasource.local.typeconverter.TvSeriesTypeConverter
import io.lzaycoe.zynema.data.model.moviedetail.MovieDetail
import io.lzaycoe.zynema.data.model.tv_series_detail.TvSeriesDetail

@TypeConverters(MovieTypeConverter::class, TvSeriesTypeConverter::class)
@Database(version = 1, entities = [MovieDetail::class, TvSeriesDetail::class], exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getFavoriteMovieDetailDao(): FavoriteMovieDao
    abstract fun getFavoriteTvSeriesDao(): FavoriteTvSeriesDao
}