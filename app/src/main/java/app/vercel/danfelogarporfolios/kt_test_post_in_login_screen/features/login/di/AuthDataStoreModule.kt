package app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.di

import android.content.Context
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.data.repository.AuthDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthDataStoreModule {
    @Provides
    @Singleton
    fun provideAuthDataRepository(
        @ApplicationContext context: Context
    ): AuthDataRepository {
        return AuthDataRepository(context)
    }
}