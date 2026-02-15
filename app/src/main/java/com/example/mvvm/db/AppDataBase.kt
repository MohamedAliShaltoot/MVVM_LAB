
package com.example.mvvm.db
import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.mvvm.db.ProductsDao
import com.example.mvvm.data.model.Product


@Database(entities = [Product::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun productsDao(): ProductsDao

    companion object {
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            if (instance == null) {
                instance = databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "products_db"
                ).build()
            }
            return instance!!
        }
    }
}



