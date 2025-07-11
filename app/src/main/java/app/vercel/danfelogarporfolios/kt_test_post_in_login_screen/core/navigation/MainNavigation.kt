package app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.core.navigation

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.home.presentation.HomeScreen
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.presentation.LoginScreen
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.onboarding.presentation.OnboardingScreen
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.onboarding.presentation.OnboardingViewModel


@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
    onbViewModel: OnboardingViewModel = hiltViewModel()
){
    val isOnbFinished = onbViewModel.isOnBoardingFinished.collectAsState(initial = null)
    if (isOnbFinished.value == null) {
        //loading screen
        return
    }

    val startDestination = when {
        !isOnbFinished.value!! -> Screen.Onboarding
        else -> Screen.Login
    }

    val backStack = rememberNavBackStack(startDestination)
    // Use backStack to check if you can pop
    val canPop = backStack.size > 1

    //Deny access to non-allowed screens
    BackHandler(enabled = canPop) { backStack.removeLast() }
    //ui
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryDecorators = listOf(
            rememberSceneSetupNavEntryDecorator(),
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()//learn more about the lifecycle of this decorator also when let one screen know if you go back the screen need recreate it viewModel or refresh
        ),
        entryProvider = entryProvider {
            entry<Screen.Onboarding> {
                OnboardingScreen(
                    onNavigate = {
                        backStack.add(Screen.Login)
                        backStack.remove(Screen.Onboarding)
                    }
                )
            }
            entry<Screen.Login> {
                LoginScreen(
                    onNavigate = { backStack.add(Screen.Home) }
                )
            }
            entry<Screen.Home>{
                HomeScreen(
                    onNavigate = { backStack.add(Screen.Onboarding) }
                )
            }
        },
        transitionSpec = {
            //animation for left to right navigation in the screen
            ContentTransform(
                slideInHorizontally(initialOffsetX = { it }),
                slideOutHorizontally(targetOffsetX = { -it })
            )
        },
        popTransitionSpec = {
            //animation for right to left navigation out of the screen
            ContentTransform(
                slideInHorizontally(initialOffsetX = { -it }),
                slideOutHorizontally(targetOffsetX = { it })
            )
        }
    )
}