package io.lzaycoe.zynema.ui.screens.movies.movie_detail
// Trọng Nghĩa
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.lzaycoe.zynema.data.datasource.local.dao.FavoriteMovieDao
import io.lzaycoe.zynema.data.model.MovieItem
import io.lzaycoe.zynema.data.model.artist.Artist
import io.lzaycoe.zynema.data.model.moviedetail.MovieDetail
import io.lzaycoe.zynema.data.repository.remote.movie.MovieRepository
import io.lzaycoe.zynema.utils.network.DataState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel
@Inject
constructor(
    private val repo: MovieRepository,
    private val movieDetailDao: FavoriteMovieDao,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState: StateFlow<MovieDetailUiState>
        get() = _uiState.asStateFlow()

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            launch { updateMovieDetail(movieId) }
            launch { updateRecommendedMovies(movieId) }
            launch { updateMovieCredit(movieId) }
        }
    }

    private suspend fun updateMovieDetail(movieId: Int) {
        repo.movieDetail(movieId).collect { result ->
            handleStateUpdate(result) { state, data -> state.copy(movieDetail = data) }
        }
    }

    private suspend fun updateRecommendedMovies(movieId: Int) {
        repo.recommendedMovie(movieId).collect { result ->
            handleStateUpdate(result) { state, data ->
                state.copy(recommendedMovies = data ?: emptyList())
            }
        }
    }

    private suspend fun updateMovieCredit(movieId: Int) {
        repo.movieCredit(movieId).collect { result ->
            handleStateUpdate(result) { state, data -> state.copy(movieCredit = data) }
        }
    }

    private fun <T> handleStateUpdate(
        result: DataState<T>,
        stateUpdater: (MovieDetailUiState, T?) -> MovieDetailUiState,
    ) {
        _uiState.update { currentState ->
            when (result) {
                is DataState.Loading -> currentState.copy(isLoading = true)
                is DataState.Success -> stateUpdater(
                    currentState,
                    result.data
                ).copy(isLoading = false)

                is DataState.Error -> currentState.copy(isLoading = false) // Optionally log error details
            }
        }
    }

    fun toggleFavorite(movieDetail: MovieDetail) {
        viewModelScope.launch {
            val existing = movieDetailDao.getMovieDetailById(movieDetail.id)
            if (existing != null) {
                movieDetailDao.deleteMovieDetailById(movieDetail.id)
            } else {
                movieDetailDao.insert(movieDetail)
            }
            observeFavoriteStatus(movieDetail.id)
        }
    }

    fun observeFavoriteStatus(movieId: Int) {
        viewModelScope.launch {
            val isFavorite = movieDetailDao.getMovieDetailById(movieId) != null
            _uiState.update { currentState -> currentState.copy(isFavorite = isFavorite) }
        }
    }
}

data class MovieDetailUiState(
    val movieDetail: MovieDetail? = null,
    val recommendedMovies: List<MovieItem> = emptyList(),
    val movieCredit: Artist? = null,
    val isLoading: Boolean = false,
    val isFavorite: Boolean = false,
)
