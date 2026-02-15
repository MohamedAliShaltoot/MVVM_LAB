package com.example.mvvm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.data.ProductRepository
import com.example.mvvm.data.model.Product
import kotlinx.coroutines.launch

class AllFavViewModel(context: Context) : ViewModel() {

    private val productRepository = ProductRepository(context)

    val deletedMsg = MutableLiveData<String>()

    val favProducts: LiveData<List<Product>> =
        productRepository.getFavProducts()

    fun deleteProductFromFav(product: Product) {
        viewModelScope.launch {
            productRepository.deleteProduct(product)
            deletedMsg.value = "Product deleted successfully"
        }
    }
}

