package com.example.mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.mvvm.ui.allProducts.AllMoviesScreen
import com.example.mvvm.ui.allProducts.AllProductsViewModel
import com.example.mvvm.ui.allProducts.AllProductsViewModelFactory
import com.example.mvvm.ui.fav.AllFavViewModel
import com.example.mvvm.ui.fav.FavMoviesScreen
import kotlin.system.exitProcess


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") { MainScreen(navController) }

        composable("all_movies") {
            val context = LocalContext.current.applicationContext
            val factory = remember { AllProductsViewModelFactory(context) }

            val viewModel: AllProductsViewModel = viewModel(factory = factory)


            AllMoviesScreen(
                products = viewModel.allProduct.observeAsState().value ?: emptyList(),
                error = viewModel.error.observeAsState().value,
                addToFav = { viewModel.insertProductToFav(it) }
            )
        }
        composable("fav_movies") {
            val context = LocalContext.current.applicationContext
            val viewModel: AllFavViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return AllFavViewModel(context) as T
                    }
                }
            )

            FavMoviesScreen(
                products = viewModel.favProducts.observeAsState(emptyList()).value,
                error = viewModel.deletedMsg.observeAsState().value,
                deleteFromFav = { viewModel.deleteProductFromFav(it) }
            )
        }
    }

}

@Composable
fun MainScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("MVVM LAB", fontSize = 30.sp,color = Color.Black)
        Spacer(modifier = Modifier.height(46.dp))
        Button(
            onClick = {
                navController.navigate("all_movies")
            },
            modifier = Modifier.width(280.dp).height(40.dp), colors = ButtonDefaults.buttonColors(Color.Gray)
        ) {
            Text("All Movies")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("fav_movies")
            },
            modifier = Modifier.width(280.dp).height(40.dp),colors = ButtonDefaults.buttonColors(Color.Gray)
        ) {
            Text("Favorite Movies")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                exitProcess(0)
            },
            modifier = Modifier.width(280.dp).height(40.dp),colors = ButtonDefaults.buttonColors(Color.Gray)
        ) {
            Text("Exit")
        }
    }
}
