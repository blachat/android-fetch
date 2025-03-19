package com.lachat.fetchrewards.network

import com.lachat.fetchrewards.model.FetchItem
import retrofit2.http.GET

interface ApiService {
    @GET("hiring.json")
    suspend fun getFetchItems(): List<FetchItem>
}