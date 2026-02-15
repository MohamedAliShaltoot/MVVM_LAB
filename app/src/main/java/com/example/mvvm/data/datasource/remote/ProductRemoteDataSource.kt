package com.example.mvvm.data.datasource.remote


import com.example.mvvm.network.ProductService
import com.example.mvvm.data.model.Product
import com.example.mvvm.network.Network



class ProductRemoteDataSource {
    private val productService: ProductService? = Network.instance?.productService

    suspend fun getProducts(): List<Product> {
        println("API CALL STARTED")

        val response = productService?.products()

        println("RAW RESPONSE = $response")
        println("PRODUCTS SIZE = ${response?.products?.size}")

        return response?.products ?: emptyList()
    }

}
