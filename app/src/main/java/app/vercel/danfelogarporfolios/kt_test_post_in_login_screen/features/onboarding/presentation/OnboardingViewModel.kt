package app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.onboarding.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.onboarding.data.OnboardingPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val onbPrefsRepo: OnboardingPreferencesRepository
): ViewModel() {

    val isOnBoardingFinished = onbPrefsRepo.isOnbFinished

    fun finishOnboarding() {
        viewModelScope.launch {
            onbPrefsRepo.saveIsOnbFinished(true)
        }
    }
}