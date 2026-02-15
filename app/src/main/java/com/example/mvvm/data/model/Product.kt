package com.example.mvvm.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey
    val id: Int,

    val title: String?,
    val brand: String?,
    val price: Double,
    val rating: Double,
    val description: String?,
    val thumbnail: String?
)

