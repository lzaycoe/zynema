package io.lzaycoe.zynema.data.repository.local.movie

import io.lzaycoe.zynema.data.datasource.local.dao.FavoriteMovieDao
import io.lzaycoe.zynema.data.model.moviedetail.MovieDetail
import javax.inject.Inject

class LocalMovieRepository
@Inject
constructor(
    private val movieDao: FavoriteMovieDao,
) : LocalMovieRepositoryInterface {

    override suspend fun favoriteMovies(): List<MovieDetail?> {
        return movieDao.getAllMovieDetails()
    }

    override suspend fun removeMovieById(movieId: Int) {
        movieDao.deleteMovieDetailById(movieId)
    }
}
