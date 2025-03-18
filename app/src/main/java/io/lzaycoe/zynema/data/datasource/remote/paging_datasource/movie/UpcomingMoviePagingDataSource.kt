package io.lzaycoe.zynema.data.datasource.remote.paging_datasource.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.lzaycoe.zynema.data.datasource.remote.ApiService
import io.lzaycoe.zynema.data.model.MovieItem
import java.io.IOException
import javax.inject.Inject
import retrofit2.HttpException
import timber.log.Timber

class UpcomingMoviePagingDataSource
@Inject
constructor(private val apiService: ApiService, private val genreId: String?) :
    PagingSource<Int, MovieItem>() {

  override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
    return state.anchorPosition
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
    return try {
      val nextPage = params.key ?: 1
      val movieList = apiService.upcomingMovies(nextPage, genreId)
      LoadResult.Page(
          data = movieList.results,
          prevKey = if (nextPage == 1) null else nextPage - 1,
          nextKey = if (movieList.results.isNotEmpty()) movieList.page + 1 else null)
    } catch (exception: IOException) {
      Timber.e("exception ${exception.message}")
      return LoadResult.Error(exception)
    } catch (httpException: HttpException) {
      Timber.e("httpException ${httpException.message}")
      return LoadResult.Error(httpException)
    }
  }
}
