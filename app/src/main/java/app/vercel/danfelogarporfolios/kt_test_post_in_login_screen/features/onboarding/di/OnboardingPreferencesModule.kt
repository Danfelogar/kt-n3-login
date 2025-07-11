package app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.onboarding.di

import android.content.Context
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.onboarding.data.OnboardingPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OnboardingPreferencesModule {
    @Provides
    @Singleton
    fun provideOnboardingPreferencesRepository(
        @ApplicationContext context: Context
    ): OnboardingPreferencesRepository {
        return OnboardingPreferencesRepository(context)
    }
}