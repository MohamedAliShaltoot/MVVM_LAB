package com.example.mvvm
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mvvm.data.ProductRepository
import com.example.mvvm.data.model.Product
import kotlinx.coroutines.launch

class AllProductsViewModel(context: Context) : ViewModel() {

    private val productRepository: ProductRepository = ProductRepository(context)
val _allProduct : MutableLiveData<List<Product>> = MutableLiveData()

val allProduct : LiveData<List<Product>>
    get()=_allProduct
val _error : MutableLiveData<String> = MutableLiveData()
    val error : LiveData<String>
        get()=_error
    init {
        allProducts()
    }

fun allProducts() {
    viewModelScope.launch {
        try {
            val products = productRepository.getProducts()
            _allProduct.value = products
        } catch (e: Exception) {
            _error.value = e.message
            e.printStackTrace()
        }
    }

}



    fun insertProductToFav(product: Product) {
          viewModelScope.launch{
              productRepository.insertProduct(product)
          }
    }
}
class AllProductsViewModelFactory(private val context: Context) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AllProductsViewModel(context) as T
    }
}