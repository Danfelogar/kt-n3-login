package app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.data.services

import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.domine.model.LoginRequest
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.domine.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


// POST: Login
interface AuthApiService {
    @POST("auth/login")
    suspend fun login(
        @Header("Authorization") authHeader: String? = null,
        @Body request: LoginRequest
    ): LoginResponse
}