package app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.onboarding.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.core.components.carouselOnb.CarouselOnboarding

@Composable
fun OnboardingScreen(
    onNavigate: () -> Unit = { /* No-op */ },
) {

    Column {
        Text(
            text = "Welcome to the Onboarding Screen!",
        )
        CarouselOnboarding()
    }
}