package com.example.mvvm.data.datasource.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.mvvm.data.model.Product
import com.example.mvvm.db.AppDataBase
import com.example.mvvm.db.ProductsDao

//class ProductLocalDataSource(context: Context) {
//    var productsDao: ProductsDao
//
//    init {
//        productsDao = AppDataBase.getInstance(context).productsDao()
//    }
//
//     fun insertProduct(product: Product) {
//
//            productsDao.inertProduct(product)
//
//    }
//
//    fun deleteProduct(product: Product) {
//
//            productsDao.deleteProduct(product)
//
//    }
//
//    fun products() : LiveData<MutableList<Product>>{
//        return productsDao.products()
//    }
//
//}
class ProductLocalDataSource(context: Context) {

    private val productsDao = AppDataBase.getInstance(context).productsDao()

    suspend fun insertProduct(product: Product) {
        productsDao.insertProduct(product)
    }

    suspend fun deleteProduct(product: Product) {
        productsDao.deleteProduct(product)
    }

    fun getProducts(): LiveData<List<Product>> {
        return productsDao.getAllProducts()
    }
}

