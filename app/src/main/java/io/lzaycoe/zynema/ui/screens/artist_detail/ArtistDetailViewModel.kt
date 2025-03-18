package io.lzaycoe.zynema.ui.screens.artist_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.lzaycoe.zynema.data.model.artist.ArtistDetail
import io.lzaycoe.zynema.data.model.artist.ArtistMovie
import io.lzaycoe.zynema.data.repository.remote.artist.ArtistRepository
import io.lzaycoe.zynema.utils.network.DataState
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ArtistDetailViewModel
@Inject
constructor(
    private val repo: ArtistRepository,
) : ViewModel() {

  private val _uiState = MutableStateFlow(ArtistDetailUiState())
  val uiState: StateFlow<ArtistDetailUiState>
    get() = _uiState.asStateFlow()

  fun fetchArtistDetails(personId: Int) {
    viewModelScope.launch {
      launch { artistDetail(personId) }
      launch { artistMovies(personId) }
    }
  }

  private suspend fun artistDetail(personId: Int) {
    repo.artistDetail(personId).collect { result ->
      handleStateUpdate(result) { state, data -> state.copy(artistDetail = data) }
    }
  }

  private suspend fun artistMovies(personId: Int) {
    repo.artistAllMovies(personId).collect { result ->
      handleStateUpdate(result) { state, data -> state.copy(artistMovies = data?.cast) }
    }
  }

  private fun <T> handleStateUpdate(
      result: DataState<T>,
      stateUpdater: (ArtistDetailUiState, T?) -> ArtistDetailUiState,
  ) {
    _uiState.update { currentState ->
      when (result) {
        is DataState.Loading -> currentState.copy(isLoading = true)
        is DataState.Success -> stateUpdater(currentState, result.data).copy(isLoading = false)

        is DataState.Error -> currentState.copy(isLoading = false) // Optionally log error details
      }
    }
  }
}

data class ArtistDetailUiState(
    val artistDetail: ArtistDetail? = null,
    val artistMovies: List<ArtistMovie>? = null,
    val isLoading: Boolean = false,
)
