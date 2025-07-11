package app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.core.components.carouselOnb


import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.R
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.ui.theme.CustomShapes
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.ui.theme.Shapes
import coil3.compose.AsyncImage
import kotlin.math.abs


@Composable
fun CarouselOnboarding(
    onNavigate: () -> Unit,
    viewModel: CarouselOnbViewModel = hiltViewModel()
) {
    // state
    val currentPage = viewModel.currentPage
    val pagerState = rememberPagerState(
        pageCount = { dummyOnboardingContentList.size },
        initialPage = currentPage,
    )

    LaunchedEffect(currentPage) {
        pagerState.animateScrollToPage(
            page = currentPage,
            animationSpec = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            )
        )
    }

    //ui
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 0.dp),
                modifier = Modifier
                    .fillMaxWidth()

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
                    colors = CardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        AsyncImage(
                            model = dummyOnboardingContentList[page].image,
                            placeholder = painterResource(R.drawable.img_placeholder),
                            error = painterResource(R.drawable.image_not_found),
                            contentDescription = stringResource(dummyOnboardingContentList[page].title),
                            modifier = Modifier
                                .width(300.dp)
                                .height(320.dp)
                                .clip(Shapes.large),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        text = stringResource(dummyOnboardingContentList[page].title),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, bottom = 16.dp)
                    )
                    Text(
                        text = stringResource(dummyOnboardingContentList[page].content),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 22.dp)
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(dummyOnboardingContentList.size) { index ->
                    val color = if (pagerState.currentPage == index) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSecondary
                    }
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .clip(CustomShapes.Circle)
                            .background(color = color)
                            .height(10.dp)
                            .width(10.dp)
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (currentPage != 0) {
                FilledTonalButton(
                    onClick = { viewModel.onPageChange() },
                    modifier = Modifier
                        .width(200.dp)
                        .height(40.dp)
                ) {
                    Text(
                        text = "PREVIOUS",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                }
            } else {
                Spacer(
                    modifier = Modifier
                        .width(200.dp)
                        .height(40.dp)
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
            )
            Button(
                onClick = {
                    if(currentPage < dummyOnboardingContentList.size - 1) {
                        viewModel.onPageChange(forward = true)
                    } else {
                        onNavigate()
                    }
                },
                modifier = Modifier
                    .width(200.dp)
                    .height(40.dp)
            ) {
                Text(
                    text = "NEXT",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
            }

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
    CarouselOnboarding(
        onNavigate = { /* No-op */ },
    )
}

