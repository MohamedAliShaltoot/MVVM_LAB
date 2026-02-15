package com.example.mvvm.data

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.mvvm.data.datasource.local.ProductLocalDataSource
import com.example.mvvm.data.datasource.remote.ProductRemoteDataSource
import com.example.mvvm.data.model.Product

class ProductRepository(context: Context) {

    private val productRemoteDataSource = ProductRemoteDataSource()
    private val productLocalDataSource = ProductLocalDataSource(context)

    suspend fun getProducts(): List<Product> {
        return productRemoteDataSource.getProducts()
    }

    fun getFavProducts(): LiveData<List<Product>> {
        return productLocalDataSource.getProducts()
    }

    suspend fun insertProduct(product: Product) {
        productLocalDataSource.insertProduct(product)
    }

    suspend fun deleteProduct(product: Product) {
        productLocalDataSource.deleteProduct(product)
    }
}
