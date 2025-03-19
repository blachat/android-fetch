package com.lachat.fetchrewards.repository

import com.lachat.fetchrewards.model.FetchItem
import com.lachat.fetchrewards.network.RetrofitClient

class Repository {
    private val apiService = RetrofitClient.apiService

    suspend fun getUsers(): List<FetchItem> {
        return apiService.getFetchItems()
    }
}