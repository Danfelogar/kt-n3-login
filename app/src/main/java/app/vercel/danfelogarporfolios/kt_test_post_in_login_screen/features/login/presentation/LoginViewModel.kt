package app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.core.helpers.ValidationRules
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.core.helpers.validator
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.data.repository.AuthDataRepository
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.domine.useCase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val authDataRepository: AuthDataRepository
) : ViewModel() {
    //states
    private val _email = mutableStateOf("")
    val email: State<String> get() = _email
    private val _emailTouched = mutableStateOf(false)

    private val _password = mutableStateOf("")
    val password: State<String> get() = _password
    private val _passwordTouched = mutableStateOf(false)

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    val authData = authDataRepository.authData

    //Validations
    private val emailValidator = validator(
        ValidationRules.EMAIL,
        ValidationRules.REQUIRED,
        ValidationRules.MIN_LENGTH(5),
        ValidationRules.MAX_LENGTH(30),
    )

    private val passwordValidator = validator(
        ValidationRules.REQUIRED,
        ValidationRules.REQUIRES_DIGIT,
        ValidationRules.MIN_LENGTH(8),
        ValidationRules.REQUIRES_UPPERCASE,
        ValidationRules.REQUIRES_SPECIAL_CHAR
    )


//    private val passwordValidator = validator(
//        ValidationRules.CUSTOM("") { value ->
//            value.isEmpty() || value.length >= 8
//        },
//        ValidationRules.CUSTOM("Debe contener al menos 8 caracteres") { value ->
//            value.isEmpty() || value.length >= 8
//        },
//        ValidationRules.CUSTOM("Debe contener un número") { value ->
//            value.isEmpty() || value.any { it.isDigit() }
//        },
//        ValidationRules.CUSTOM("Debe contener una mayúscula") { value ->
//            value.isEmpty() || value.any { it.isUpperCase() }
//        },
//        ValidationRules.CUSTOM("Debe contener un caracter especial") { value ->
//            val specialChars = "!@#$%^&*()_+"
//            value.isEmpty() || value.any { it in specialChars }
//        }
//    )

    val isFormValid = derivedStateOf {
        (!_emailTouched.value || emailError.value == null) && (!_passwordTouched.value || passwordError.value == null)
    }

//    val isFormValid = derivedStateOf {
//        (!_emailTouched.value || emailError.value == null) && (password.value.isEmpty() || passwordError.value == null)
//    }

    //errors
    private val _emailError = mutableStateOf<String?>(null)
    val emailError: State<String?> get() = _emailError

    private val _passwordError = mutableStateOf<String?>(null)
    val passwordError: State<String?> get() = _passwordError

    //actions
    fun setEmail(value: String) {
        _email.value = value
        _emailTouched.value = true
        _emailError.value = emailValidator.validate(value)
    }

    fun setPassword(value: String) {
        _password.value = value
        _passwordTouched.value = true
        _passwordError.value = passwordValidator.validate(value)
    }

    fun validateAll(): Boolean {
        _emailError.value = emailValidator.validate(_email.value)
        _passwordError.value = passwordValidator.validate(_password.value)

        return _emailError.value == null &&
                _passwordError.value == null
    }

    fun login(onNavigate: () -> Unit,) {
        _emailTouched.value = true
        _passwordTouched.value = true
        if (validateAll()) {
            viewModelScope.launch {
                _isLoading.value = true
                try {
                    // Simulate a network call or authentication process
                    val response = loginUseCase(email.value, password.value)
                    authDataRepository.saveAuthData(response)
                    onNavigate()
                } catch (e: Exception) {
                    Log.e(TAG, "Login failed: ${e.message}")
                } finally {
                    _isLoading.value = false
                }
            }
        }
    }

    private companion object {
        const val TAG = "LoginViewModel"
    }
}