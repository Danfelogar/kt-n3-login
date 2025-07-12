package app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.di

import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.data.repository.AuthDataRepository
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.data.repository.AuthRepositoryImpl
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.data.services.AuthApiService
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.domine.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

// AuthModule.kt en features/login/di
@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository {
        return impl
    }
}