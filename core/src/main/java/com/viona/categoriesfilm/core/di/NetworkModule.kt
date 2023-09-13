package com.viona.categoriesfilm.core.di

import com.viona.categoriesfilm.core.BuildConfig
import com.viona.categoriesfilm.core.data.source.network.ApiService
import com.viona.categoriesfilm.core.util.retrofit
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    private val authInterceptor = Interceptor { chain ->
        val request = chain.request()
            .newBuilder()
            .addHeader(
                "Authorization",
                "Bearer ${BuildConfig.api_key}"
            )
            .build()

        chain.proceed(request)

    }

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(logging)
        .build()

    private val retrofitData = retrofit {
        baseUrl("https://api.themoviedb.org/3/")
        addConverterFactory(GsonConverterFactory.create())
        client(client)
        build()
    }

    @Provides
    fun provideApiService(): ApiService {
        return retrofitData.create(ApiService::class.java)
    }
}