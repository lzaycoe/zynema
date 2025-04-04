package io.lzaycoe.zynema.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import io.lzaycoe.zynema.data.datasource.remote.ApiURL
import io.lzaycoe.zynema.ui.theme.DefaultBackgroundColor
import io.lzaycoe.zynema.ui.theme.SecondaryFontColor
import io.lzaycoe.zynema.ui.theme.cornerRadius

@Composable
fun <T> ItemView(
    item: T,
    navController: NavController,
    itemIdExtractor: (T) -> String,
    itemImageUrlExtractor: (T) -> String,
    itemDetailRoute: String
) {
    Column(modifier = Modifier.padding(5.dp)) {
        CoilImage(
            modifier =
                Modifier
                    .size(250.dp)
                    .cornerRadius(10)
                    .clickable {
                        navController.navigate("$itemDetailRoute/${itemIdExtractor(item)}")
                    },
            imageModel = { ApiURL.IMAGE_URL + itemImageUrlExtractor(item) },
            imageOptions =
                ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                    contentDescription = "Item"
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
                })
    }
}
