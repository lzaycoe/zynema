package io.lzaycoe.zynema.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import io.lzaycoe.zynema.R
import io.lzaycoe.zynema.data.datasource.remote.ApiURL
import io.lzaycoe.zynema.data.model.SearchItem
import io.lzaycoe.zynema.navigation.Screen
import io.lzaycoe.zynema.ui.theme.DefaultBackgroundColor
import io.lzaycoe.zynema.ui.theme.FontColor
import io.lzaycoe.zynema.ui.theme.SecondaryFontColor
import io.lzaycoe.zynema.ui.theme.cornerRadius
import io.lzaycoe.zynema.utils.ACTIVE_MOVIE_TAB
import io.lzaycoe.zynema.utils.ACTIVE_TV_SERIES_TAB
import io.lzaycoe.zynema.utils.roundTo

@Composable
fun SearchUI(
    navController: NavController,
    searchData: List<SearchItem>,
    activeTab: Int,
    itemClick: () -> Unit,
) {
    LazyColumn(
        modifier =
            Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .heightIn(0.dp, 350.dp) // define max height
                .clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
                .background(color = DefaultBackgroundColor)
                .padding(top = 8.dp)
    ) {
        items(
            items = searchData,
            itemContent = { item ->
                Row(
                    modifier =
                        Modifier
                            .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
                            .clickable {
                                itemClick.invoke()
                                if (activeTab == ACTIVE_MOVIE_TAB) {
                                    navController.navigate(Screen.MovieDetail.route.plus("/${item.id}"))
                                } else if (activeTab == ACTIVE_TV_SERIES_TAB) {
                                    navController.navigate(Screen.TvSeriesDetail.route.plus("/${item.id}"))
                                }
                            }) {
                    CoilImage(
                        modifier = Modifier
                            .height(100.dp)
                            .width(80.dp)
                            .cornerRadius(8),
                        imageModel = { ApiURL.IMAGE_URL.plus(item.backdropPath) },
                        imageOptions =
                            ImageOptions(
                                contentScale = ContentScale.Crop,
                                alignment = Alignment.Center,
                                contentDescription = "search item",
                                colorFilter = null,
                            ),
                        component =
                            rememberImageComponent {
                                +CircularRevealPlugin(duration = 800)
                                +ShimmerPlugin(
                                    shimmer =
                                        Shimmer.Flash(
                                            baseColor = SecondaryFontColor,
                                            highlightColor = DefaultBackgroundColor
                                        )
                                )
                            },
                    )
                    Column {
                        val title = if (activeTab == ACTIVE_MOVIE_TAB) item.title else item.name
                        val release =
                            if (activeTab == ACTIVE_MOVIE_TAB) item.releaseDate else item.firstAirDate
                        Text(
                            text = title ?: "",
                            modifier = Modifier.padding(start = 8.dp, top = 4.dp),
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = release ?: "",
                            color = FontColor,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        Text(
                            text =
                                "${stringResource(R.string.rating_search)} ${
                                    item.voteAverage?.roundTo(
                                        1
                                    )
                                }",
                            color = SecondaryFontColor,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            })
    }
}
