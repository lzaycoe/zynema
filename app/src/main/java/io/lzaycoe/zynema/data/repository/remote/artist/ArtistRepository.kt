package io.lzaycoe.zynema.data.repository.remote.artist

import io.lzaycoe.zynema.data.datasource.remote.ApiService
import io.lzaycoe.zynema.data.model.artist.ArtistDetail
import io.lzaycoe.zynema.data.model.artist.ArtistMovies
import io.lzaycoe.zynema.utils.network.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ArtistRepository @Inject constructor(
    private val apiService: ApiService,
) : ArtistRepositoryInterface {
    override suspend fun artistAllMovies(movieId: Int): Flow<DataState<ArtistMovies>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.artistAllMovies(movieId)
            emit(DataState.Success(searchResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
    override suspend fun artistDetail(personId: Int): Flow<DataState<ArtistDetail>> = flow {
        emit(DataState.Loading)
        try {
            val artistDetailResult = apiService.artistDetail(personId)
            emit(DataState.Success(artistDetailResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}