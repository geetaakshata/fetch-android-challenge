package com.example.fetchchallenge.data.repository

import com.example.fetchchallenge.data.api.Api
import com.example.fetchchallenge.data.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemRepository {
    private val apiService = Api.apiService

    suspend fun getItems(): Result<List<Item>> {
        return withContext(Dispatchers.IO) {
            try {
                Result.success(apiService.getItems())
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}