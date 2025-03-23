package io.lzaycoe.zynema.ui.screens.celebrities.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.lzaycoe.zynema.data.model.GenreId
import io.lzaycoe.zynema.data.repository.remote.celebrity.CelebrityRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class PopularCelebritiesViewModel @Inject constructor(val repo: CelebrityRepository) : ViewModel() {
    val filterData = MutableStateFlow<GenreId?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val popularCelebrities =
        filterData.flatMapLatest { repo.popularCelebrities(1) }.cachedIn(viewModelScope)
}
