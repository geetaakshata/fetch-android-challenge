package com.example.fetchchallenge.data.api

import com.example.fetchchallenge.data.model.Item
import retrofit2.http.GET

interface ApiService {
    @GET("hiring.json")
    suspend fun getItems(): List<Item>
}