package com.example.mvvm.network


import com.example.mvvm.data.model.ProductResponse
import retrofit2.http.GET


interface ProductService {
    @GET("products")
    suspend fun products(): ProductResponse
}

