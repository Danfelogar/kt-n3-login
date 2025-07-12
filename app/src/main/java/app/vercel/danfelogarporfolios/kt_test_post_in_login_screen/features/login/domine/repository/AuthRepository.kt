package app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.domine.repository

import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.domine.model.LoginResponse

interface AuthRepository {
    suspend fun login(
        email: String,
        password: String,
        authToken: String? = null
    ): LoginResponse
}