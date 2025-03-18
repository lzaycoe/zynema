package io.lzaycoe.zynema.data.repository.remote.artist

import io.lzaycoe.zynema.data.model.artist.ArtistDetail
import io.lzaycoe.zynema.data.model.artist.ArtistMovies
import io.lzaycoe.zynema.utils.network.DataState
import kotlinx.coroutines.flow.Flow

interface ArtistRepositoryInterface {
  suspend fun artistAllMovies(movieId: Int): Flow<DataState<ArtistMovies>>

  suspend fun artistDetail(personId: Int): Flow<DataState<ArtistDetail>>
}
