package com.bulgakov.bestmovie.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bulgakov.bestmovie.BuildConfig
import com.bulgakov.bestmovie.api.MovieService
import com.bulgakov.bestmovie.api.Result
import com.bulgakov.bestmovie.utils.ORDER
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MoviePagingSource @Inject constructor(
    private val service: MovieService,
) : PagingSource<Int, Result>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val position = params.key ?: 0

        return try {
            val response = service.getMovies(BuildConfig.API_KEY, position, ORDER)
            val results = response.results

            val nextKey = if (results.isEmpty()) {
                null
            } else {
                position + params.loadSize
            }

            LoadResult.Page(
                data = results,
                prevKey = if (position == 0) null else position - 1,
                nextKey = nextKey
            )

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}