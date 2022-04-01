package com.avility.meli.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.avility.meli.presentation.product_detail.ProductDetailScreen
import com.avility.meli.presentation.product_list.ProductListScreen
import com.avility.meli.presentation.splash_screen.SplashScreen

/**
 * Para gestionar la navegacion de cada una de las pantallas
 */
@ExperimentalUnitApi
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination =  Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.SearchProductScreen.route) {
            ProductListScreen(navController = navController)
        }
        composable(Screen.DetailProductScreen.route + "/{productId}") {
            ProductDetailScreen(navController = navController)
        }
    }
}