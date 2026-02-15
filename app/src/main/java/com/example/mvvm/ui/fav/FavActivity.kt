package com.example.mvvm.ui.fav

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.mvvm.data.model.Product
@Composable
fun FavMoviesScreen(
    products: List<Product>,
    //isLoading: Boolean,
    error: String?,
    deleteFromFav: (Product) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // RecyclerView -> LazyColumn
        if (products.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(products) { products ->
                    FavProductItem(product = products!!) {
                        deleteFromFav(products)
                    }
                }
            }
        }


        // Error state
        if (error != null) {
            Text(
                text = error,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FavProductItem(product: Product, deleteFromFav: (Product) -> Unit) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),

                elevation = CardDefaults.cardElevation(4.dp) ,
        shape = RoundedCornerShape(8.dp) ,
        colors = CardDefaults.cardColors(Color(0xFFE85C90)),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            GlideImage(
                model = product.thumbnail,
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Column {
                Text(text = product.title ?: "")
                Spacer(Modifier.height(15.dp))
                Text(text = "Price: ${product.price}")
                Spacer(Modifier.height(15.dp))
                Button(onClick = { deleteFromFav(product)
                    Toast.makeText(
                        context,
                        product.title+" deleted from favourite",
                        Toast.LENGTH_SHORT
                    ).show(
                    )


                },shape = RoundedCornerShape(8.dp), colors = ButtonDefaults.buttonColors(Color.Red)

                    ) {
                    Text(text = "Delete from Favorite")
                }
            }
        }
    }
}
