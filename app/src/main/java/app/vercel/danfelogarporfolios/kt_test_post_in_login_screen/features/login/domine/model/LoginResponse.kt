package app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.domine.model

import com.google.gson.annotations.SerializedName


data class LoginResponse(
    @SerializedName("id") val id: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("token") val token: String,
    )