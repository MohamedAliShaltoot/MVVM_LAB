package com.example.mvvm.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvm.data.model.Product


//@Dao
//interface ProductsDao {
//    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
//     fun inertProduct(product: Product)
//
//    @Query("SELECT * FROM products")
//     fun products(): LiveData<MutableList<Product>>
//
//    @Delete
//    fun deleteProduct(product: Product)
//}

@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product: Product)

    @Query("SELECT * FROM products")
    fun getAllProducts(): LiveData<List<Product>>

    @Delete
    suspend fun deleteProduct(product: Product)
}
