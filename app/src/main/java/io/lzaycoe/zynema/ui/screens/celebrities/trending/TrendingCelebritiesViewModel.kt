package io.lzaycoe.zynema.ui.screens.celebrities.trending

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.lzaycoe.zynema.data.model.GenreId
import io.lzaycoe.zynema.data.repository.remote.celebrity.CelebrityRepository
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

@HiltViewModel
class TrendingCelebritiesViewModel @Inject constructor(val repo: CelebrityRepository) :
    ViewModel() {
  val filterData = MutableStateFlow<GenreId?>(null)

  @OptIn(ExperimentalCoroutinesApi::class)
  val trendingCelebrities =
      filterData.flatMapLatest { repo.trendingCelebrities(1) }.cachedIn(viewModelScope)
}
