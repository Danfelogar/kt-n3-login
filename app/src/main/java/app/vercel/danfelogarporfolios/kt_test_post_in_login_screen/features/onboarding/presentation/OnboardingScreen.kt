package app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.onboarding.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.core.components.carouselOnb.CarouselOnboarding

@Composable
fun OnboardingScreen(
    onNavigate: () -> Unit = { /* No-op */ },
    onbViewModel: OnboardingViewModel = hiltViewModel()
) {
    //state
    //ui
    CarouselOnboarding(
        onNavigate = {
            onbViewModel.finishOnboarding()
            onNavigate()
        }
    )
}