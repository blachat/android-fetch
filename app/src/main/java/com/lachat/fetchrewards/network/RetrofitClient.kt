package com.lachat.fetchrewards.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.lachat.fetchrewards.BuildConfig

object RetrofitClient {
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}