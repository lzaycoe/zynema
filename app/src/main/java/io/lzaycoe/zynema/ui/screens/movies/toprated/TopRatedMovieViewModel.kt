package io.lzaycoe.zynema.ui.screens.movies.toprated

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.lzaycoe.zynema.data.model.GenreId
import io.lzaycoe.zynema.data.model.moviedetail.Genre
import io.lzaycoe.zynema.data.repository.remote.movie.MovieRepository
import io.lzaycoe.zynema.utils.AppConstant
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

@HiltViewModel
class TopRatedMovieViewModel @Inject constructor(val repo: MovieRepository) : ViewModel() {
  var selectedGenre: MutableState<Genre> =
      mutableStateOf(Genre(null, AppConstant.DEFAULT_GENRE_ITEM))
  val filterData = MutableStateFlow<GenreId?>(null)

  @OptIn(ExperimentalCoroutinesApi::class)
  val topRatedMovies =
      filterData
          .flatMapLatest { repo.topRatedMoviePagingDataSource(it?.genreId) }
          .cachedIn(viewModelScope)
}
