package io.lzaycoe.zynema.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import io.lzaycoe.zynema.R
import io.lzaycoe.zynema.Utlis.ProgressBarHandler
import io.lzaycoe.zynema.adapter.DiscoverMovieAdapter
import io.lzaycoe.zynema.adapter.LoaderStateAdapter
import io.lzaycoe.zynema.adapter.TrailerAdapter
import io.lzaycoe.zynema.databinding.ActivityDiscoverAllMovieBinding
import io.lzaycoe.zynema.databinding.ActivityMovieDetailsBinding
import io.lzaycoe.zynema.network.NetworkingConstants
import io.lzaycoe.zynema.repo.DiscoverMoveViewModel
import io.lzaycoe.zynema.repo.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetails : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding
    private val mainViewModel: MovieViewModel by viewModels()
    var movieId: String? = null
    var progressBar: ProgressBarHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initProgress();
        initApiCalls();


    }

    private fun initApiCalls() {
        movieId = intent.getStringExtra("MovieIdPass").toString()
        mainViewModel.getMovieDetails(movieId!!)
        mainViewModel.getTrailerResponse(movieId!!)
        binding.backButton.setOnClickListener {
            finish()
        }


        mainViewModel.movieDetailLivedata.observe(this@MovieDetails, Observer { response ->
            progressBar?.hide()
            binding.apply {

                movieBackdropImage.load(NetworkingConstants.BASE_BACKDROP_PATH + response.backdropPath)
                moviePosterImage.load(NetworkingConstants.BASE_POSTER_PATH + response.posterPath)
                movieTitleText.text = response.title
                movieOverviewText.text = response.overview
                movieInfoText.text = response.releaseDate
            }
        })
        mainViewModel.getTrailerLiveData.observe(this, Observer {
            it?.let {
                binding.apply {
                    recyclerviewTrailer.apply {
                        recyclerviewTrailer.adapter =
                            TrailerAdapter(
                                it
                            ) {
                                val intent = Intent(Intent.ACTION_VIEW)
                                intent.data =
                                    Uri.parse(NetworkingConstants.YOUTUBE_VIDEO_URL + it.key)
                                startActivity(intent)
                            }
                        layoutManager = LinearLayoutManager(
                            this@MovieDetails,
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )

                    }

                }
            }
        })
    }

    private fun initProgress() {
        progressBar = ProgressBarHandler(this)
        progressBar!!.show()
    }


}

