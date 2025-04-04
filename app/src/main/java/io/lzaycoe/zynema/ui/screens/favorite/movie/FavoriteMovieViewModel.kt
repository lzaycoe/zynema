package io.lzaycoe.zynema.ui.screens.favorite.movie
// Mỹ Linh
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.lzaycoe.zynema.data.model.moviedetail.MovieDetail
import io.lzaycoe.zynema.data.repository.local.movie.LocalMovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteMovieViewModel
@Inject
constructor(
    private val localMovieRepo: LocalMovieRepository,
) : ViewModel() {
    private val _favoriteMovies = MutableStateFlow<List<MovieDetail?>>(arrayListOf())
    val favoriteMovies
        get() = _favoriteMovies.asStateFlow()

    fun favoriteMoviesFromDB() {
        viewModelScope.launch { _favoriteMovies.value = localMovieRepo.favoriteMovies() }
    }

    fun removeMovieFromDB(movieId: Int) {
        viewModelScope.launch { localMovieRepo.removeMovieById(movieId) }
    }
}
