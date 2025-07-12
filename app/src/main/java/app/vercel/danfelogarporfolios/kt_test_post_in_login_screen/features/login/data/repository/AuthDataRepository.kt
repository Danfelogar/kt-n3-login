package app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.dataStore
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.AuthData
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.UserInfo
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.data.UserPrefsSerializer
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.domine.model.LoginResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthDataRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val Context.userDataStore: DataStore<AuthData> by dataStore(
        fileName = "user_prefs.pb",
        serializer = UserPrefsSerializer
    )

    val authData: Flow<AuthData> = context.userDataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error reading auth data", exception)
                emit(AuthData.getDefaultInstance())
            } else {
                throw exception
            }
        }

    suspend fun saveAuthData(loginResponse: LoginResponse) {
        context.userDataStore.updateData { currentData ->
            currentData.toBuilder()
                .setToken(loginResponse.token)
                .setUserInfo(
                    UserInfo.newBuilder()
                        .setId(loginResponse.id)
                        .setEmail(loginResponse.email)
                        .setPassword(loginResponse.password)
                        .build()
                )
                .build()
        }
    }

    suspend fun  clearAuthData() {
        context.userDataStore.updateData {
            AuthData.getDefaultInstance()
        }
    }

    companion object {
        private const val TAG = "AuthDataRepository"
    }

}