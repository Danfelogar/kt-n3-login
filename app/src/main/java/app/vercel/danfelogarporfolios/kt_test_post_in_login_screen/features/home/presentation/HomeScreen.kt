package app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
    onNavigate: () -> Unit = { /* No-op */ },
) {
    Column {
        Text(
            text = "Welcome to the Home Screen!",
        )
    }
}