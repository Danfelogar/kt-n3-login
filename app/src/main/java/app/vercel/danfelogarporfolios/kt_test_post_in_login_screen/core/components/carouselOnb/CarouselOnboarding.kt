package app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.core.components.carouselOnb

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.R
import coil3.compose.AsyncImage
import kotlin.math.abs


@Composable
fun CarouselOnboarding(
    viewModel: CarouselOnbViewModel = hiltViewModel()
) {
    // state
    val currentPage = viewModel.currentPage
//    stringResource
    val pagerState = rememberPagerState(
        pageCount = { dummyOnboardingContentList.size },
        initialPage = currentPage,
    )

    //ui
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 0.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            ) { page ->
                Card(
                    modifier = Modifier
                        .graphicsLayer {
                            // Calculate the page offset which represents the relative position of this page
                            // with respect to the current center page (including fractional scroll progress)
                            val pageOffset =
                                ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction)
                            // Clamp the offset between -1 and 1 to handle adjacent pages symmetrically
                            // This ensures we only affect the immediate left and right neighbors
                            val adjustedOffset = pageOffset.coerceIn(-1f, 1f)
                            // Calculate the fraction for interpolation (1 when centered, 0 at max offset)
                            // Using absolute value makes the effect symmetric for both left and right pages
                            val fraction = 1f - abs(adjustedOffset)
                            // Apply scale interpolation - scales down when not centered
                            // 1f when centered (fraction=1), 0.85f when at edges (fraction=0)
                            scaleX = lerp(0.85f, 1f, fraction)
                            scaleY = lerp(0.85f, 1f, fraction)
                            // Apply alpha interpolation - fades out when not centered
                            // 1f when centered (fraction=1), 0.5f when at edges (fraction=0)
                            alpha = lerp(0.5f, 1f, fraction)
                        },
                    shape = RoundedCornerShape(0.dp),
                ) {
                    AsyncImage(
                        model = dummyOnboardingContentList[page].image,
                        placeholder = painterResource(R.drawable.img_placeholder),
                        error = painterResource(R.drawable.image_not_found),
                        contentDescription = stringResource(dummyOnboardingContentList[page].title),
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth(),
        ) {

        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 412,
    heightDp = 350
)
@Composable
fun PreviewCarouselOnboarding() {
    CarouselOnboarding()
}

