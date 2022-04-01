package com.avility.meli.presentation.util

/**
 * Sealed class para representar las rutas de cada una de las pantallas
 */
sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object SearchProductScreen : Screen("search_product_screen")
    object DetailProductScreen : Screen("detail_product_screen")
}
