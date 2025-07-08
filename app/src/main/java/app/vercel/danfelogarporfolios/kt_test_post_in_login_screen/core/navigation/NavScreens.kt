package app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.core.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen: NavKey {
    @Serializable
    data object Onboarding: Screen
    @Serializable
    data object  Login: Screen
    @Serializable
    data object Home: Screen
}