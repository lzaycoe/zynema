package io.lzaycoe.zynema.ui.screens.tvseries.airing_today

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.lzaycoe.zynema.data.model.GenreId
import io.lzaycoe.zynema.data.model.moviedetail.Genre
import io.lzaycoe.zynema.data.repository.remote.tvseries.TvSeriesRepository
import io.lzaycoe.zynema.utils.AppConstant
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

@HiltViewModel
class AiringTodayTvSeriesViewModel @Inject constructor(val repo: TvSeriesRepository) : ViewModel() {
  var selectedGenre: MutableState<Genre> =
      mutableStateOf(Genre(null, AppConstant.DEFAULT_GENRE_ITEM))
  val filterData = MutableStateFlow<GenreId?>(null)

  @OptIn(ExperimentalCoroutinesApi::class)
  val airingTodayTvSeries =
      filterData
          .flatMapLatest { repo.airingTodayTvSeriesPagingDataSource(it?.genreId) }
          .cachedIn(viewModelScope)
}
