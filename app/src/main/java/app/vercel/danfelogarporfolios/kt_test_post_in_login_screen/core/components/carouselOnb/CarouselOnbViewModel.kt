package app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.core.components.carouselOnb

import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.R

data class OnboardingContent(
    val id: Int,
    @DrawableRes val image: Int,
    val title: Int,
    val content: Int
)

@HiltViewModel
class CarouselOnbViewModel @Inject constructor(): ViewModel() {
    var currentPage by mutableIntStateOf(0)
        private set

    fun onPageChange(forward: Boolean = false){
        viewModelScope.launch {
            val targetPage = when {
                forward -> currentPage + 1
                !forward -> currentPage - 1
                else -> currentPage
            }
            currentPage = targetPage
        }
    }
}

var dummyOnboardingContentList = listOf(
    OnboardingContent(
        id = 1,
        image = R.drawable.onb_1,
        title = R.string.welcome_to_our_app,
        content = R.string.manage_your_information_securely
    ),
    OnboardingContent(
        id = 2,
        image = R.drawable.onb_2,
        title = R.string.all_in_one_place,
        content = R.string.access_your_data_when_you_need_it
    ),
    OnboardingContent(
        id = 3,
        image = R.drawable.onb_3,
        title = R.string.guaranteed_safety,
        content = R.string.your_data_is_protected
    )
)