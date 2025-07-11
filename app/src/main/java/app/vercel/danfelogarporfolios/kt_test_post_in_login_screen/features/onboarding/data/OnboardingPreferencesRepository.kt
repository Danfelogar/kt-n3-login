package app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.onboarding.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private const val ONB_PREFERENCES_NAME = "onboarding_prefs"

private val Context.dataStoreSupp: DataStore<Preferences> by preferencesDataStore(name = ONB_PREFERENCES_NAME)


@Singleton
class OnboardingPreferencesRepository @Inject constructor(
//    private val dataStore: DataStore<Preferences>
    @ApplicationContext context: Context
) {
    private val dataStore = context.dataStoreSupp

    val isOnbFinished: Flow<Boolean> = dataStore.data
        .catch { exception ->
            //validated there isn't problem with reading the data
            if(exception is IOException){
                Log.e(TAG ,"Error reading preferences.", exception)
                emit(emptyPreferences())
            }else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[IS_ONB_FINISHED] ?: false
        }

    private companion object {
        val IS_ONB_FINISHED = booleanPreferencesKey("is_onb_finished")

        const val TAG = "OnboardingPreferencesRepo"
    }

    suspend fun saveIsOnbFinished(isOnbFinished: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_ONB_FINISHED] = isOnbFinished
        }
    }
}