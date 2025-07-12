package app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.data.repository.AuthDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authDataRepository: AuthDataRepository
) : ViewModel() {
    //states
    val authData = authDataRepository.authData
    //functions
    fun onLogout(onNavigate: () -> Unit) {
        viewModelScope.launch {
            authDataRepository.clearAuthData()
            onNavigate()
        }
    }
}