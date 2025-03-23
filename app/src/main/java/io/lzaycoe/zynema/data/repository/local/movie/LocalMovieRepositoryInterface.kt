package io.lzaycoe.zynema.data.repository.local.movie

import io.lzaycoe.zynema.data.model.moviedetail.MovieDetail

interface LocalMovieRepositoryInterface {
    suspend fun favoriteMovies(): List<MovieDetail?>

    suspend fun removeMovieById(movieId: Int)
}
