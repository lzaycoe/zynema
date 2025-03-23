package io.lzaycoe.zynema.data.repository.remote.celebrity

import androidx.paging.PagingData
import io.lzaycoe.zynema.data.model.celebrities.Celebrity
import kotlinx.coroutines.flow.Flow

interface CelebrityRepositoryInterface {
    fun popularCelebrities(page: Int): Flow<PagingData<Celebrity>>

    fun trendingCelebrities(page: Int): Flow<PagingData<Celebrity>>
}
