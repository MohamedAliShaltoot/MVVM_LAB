package com.example.mvvm.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network private constructor() {
    @JvmField
    var productService: ProductService?

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        productService = retrofit.create<ProductService?>(ProductService::class.java)
    }


    companion object {
        @JvmStatic
        var instance: Network? = null
            get() {
                if (field == null) {
                    field = Network()
                }
                return field
            }
            private set
        private const val BASE_URL = "https://dummyjson.com/"
    }
}


