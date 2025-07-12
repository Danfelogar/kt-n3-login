package app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.data.repository

import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.data.services.AuthApiService
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.domine.model.LoginRequest
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.domine.model.LoginResponse
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.domine.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl  @Inject constructor(
    private val api: AuthApiService
): AuthRepository {

    override suspend fun login(email: String, password: String, authToken: String?): LoginResponse {
        return api.login(
            authHeader = authToken?.let { "Bearer $it" },
            request = LoginRequest(
                email = email,
                password = password
            )
        )
    }
}