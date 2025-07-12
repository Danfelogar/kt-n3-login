package app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.core.di

import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.data.services.AuthApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    //for local Host SERVICES
    private const val BASE_URL = "http://10.0.2.2:3000/api/"

    @Provides
    @Named("localHostApi")
    fun provideRetrofitLocalHost(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    fun provideLocalHostApiService(@Named("localHostApi") retrofit: Retrofit): AuthApiService =
        retrofit.create(AuthApiService::class.java)

    //generic or other services
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .build()
}