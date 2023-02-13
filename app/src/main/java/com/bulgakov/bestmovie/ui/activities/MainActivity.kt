package com.bulgakov.bestmovie.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bulgakov.bestmovie.databinding.ActivityMainBinding
import com.bulgakov.bestmovie.ui.adapters.MovieAdapter
import com.bulgakov.bestmovie.ui.adapters.MovieLoadStateAdapter
import com.bulgakov.bestmovie.ui.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MovieAdapter()
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        binding.rvMovies.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(decoration)
            this.adapter = adapter.withLoadStateHeaderAndFooter(
                header = MovieLoadStateAdapter { adapter.retry() },
                footer = MovieLoadStateAdapter { adapter.retry() }
            )
        }

        lifecycleScope.launch {
            viewModel.movieFlow
                .collectLatest (adapter::submitData)
        }

        lifecycleScope.launch {
            adapter.loadStateFlow.collect { loadState ->
                binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            }
        }

    }
}