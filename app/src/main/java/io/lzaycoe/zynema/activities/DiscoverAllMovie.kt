package io.lzaycoe.zynema.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import io.lzaycoe.zynema.Utlis.ConnectionLiveData
import io.lzaycoe.zynema.Utlis.ProgressBarHandler
import io.lzaycoe.zynema.adapter.DiscoverMovieAdapter
import io.lzaycoe.zynema.adapter.LoaderStateAdapter
import io.lzaycoe.zynema.databinding.ActivityDiscoverAllMovieBinding
import io.lzaycoe.zynema.databinding.ActivityMovieDetailsBinding
import io.lzaycoe.zynema.repo.DiscoverMoveViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DiscoverAllMovie : AppCompatActivity() {

    private lateinit var binding: ActivityDiscoverAllMovieBinding

    private val mainViewModel: DiscoverMoveViewModel by viewModels()

    var progressBar: ProgressBarHandler? = null

    private lateinit var cld: ConnectionLiveData

    @Inject
    lateinit var discoverMovieAdapter: DiscoverMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiscoverAllMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initProgressInternet()
        initRecyclerview()
        cld.observe(this) { isConnected ->

            if (isConnected) {
                lifecycleScope.launch {
                    mainViewModel.discoverMovieResult.collectLatest { response ->
                        discoverMovieAdapter.submitData(response)
                    }
                }
                progressBar?.hide()
                binding.layout1.visibility = View.VISIBLE
                binding.layout2.visibility = View.GONE
            } else {
                binding.layout1.visibility = View.GONE
                binding.layout2.visibility = View.VISIBLE
                progressBar?.hide()
            }

        }

    }

    private fun initProgressInternet() {
        cld = ConnectionLiveData(application)
        binding.toolbar.toolbrLbl.text = "All Discover Movies"
        binding.toolbar.imgbck.setOnClickListener { finish() }
        progressBar = ProgressBarHandler(this)
        progressBar!!.show()

    }


    private fun initRecyclerview() {
        binding.apply {
            recyclerviewAll.apply {
                layoutManager =
                    LinearLayoutManager(this@DiscoverAllMovie, LinearLayoutManager.VERTICAL, false)
                adapter = discoverMovieAdapter.withLoadStateHeaderAndFooter(
                    header = LoaderStateAdapter { discoverMovieAdapter::retry },
                    footer = LoaderStateAdapter { discoverMovieAdapter::retry }
                )
            }
        }
    }


}

