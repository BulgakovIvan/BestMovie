package com.bulgakov.bestmovie.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.bulgakov.bestmovie.api.MovieService
import com.bulgakov.bestmovie.data.MoviePagingSource
import com.bulgakov.bestmovie.model.Movie
import com.bulgakov.bestmovie.utils.PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(pagingSource: MoviePagingSource) : ViewModel() {

    val movieFlow = Pager(
        PagingConfig(pageSize = PAGE_SIZE, initialLoadSize = PAGE_SIZE)
    ) {
        pagingSource
    }
        .flow
        .map { pagingData ->
            pagingData.map { result ->
                Movie(
                    result.display_title ?: "",
                    result.summary_short ?: "",
                    result.multimedia?.src ?: "",
                )
            }
        }
        .cachedIn(viewModelScope)

}