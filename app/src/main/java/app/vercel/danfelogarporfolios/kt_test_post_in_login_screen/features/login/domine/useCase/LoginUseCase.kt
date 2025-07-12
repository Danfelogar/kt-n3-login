package app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.domine.useCase

import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.domine.model.LoginResponse
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.domine.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        authToken: String? = null
    ): LoginResponse {
        return repository.login(
            email = email,
            password = password,
            authToken = authToken
        )
    }
}