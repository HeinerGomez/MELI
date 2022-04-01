package com.avility.meli.presentation.product_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.avility.meli.presentation.components.GenericEmptySearch
import com.avility.meli.presentation.components.GenericError
import com.avility.meli.presentation.components.GenericLoading
import com.avility.meli.presentation.components.TextFieldSearch
import com.avility.meli.presentation.product_list.components.ProductItem
import com.avility.meli.presentation.util.Screen

/**
 * Pantalla para la busqueda de productos, se divide en 2 partes
 * Buscador: un textField para gestionar las busquedas
 * LazyColumn: Como si fuera un recycler view se encargará de renderizar los resultados de las busquedas
 */
@ExperimentalCoilApi
@ExperimentalUnitApi
@Composable
fun ProductListScreen(
    navController: NavController,
    viewModel: ProductListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)) {
        Column {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(Color(0xfffff158)),
                contentAlignment = Alignment.Center
            ) {
                TextFieldSearch(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(47.dp)
                        .padding(start = 10.dp, end = 10.dp),
                    text = viewModel.searchQuery.value,
                    hint = "Busca en mercado libre",
                    onTextChange = viewModel::searchProduct
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            LazyColumn() {
                items(state.products) { product ->
                    ProductItem(
                        productItem = product,
                        onItemClick = {
                            navController.navigate(Screen.DetailProductScreen.route + "/${product.id}")
                        }
                    )
                    Divider()
                }
            }

            // validación para determinar que no hay una busqueda y tampoco resultados de busquedas
            if (state.products.isEmpty() && state.error.isBlank() && !state.isLoading) {
                GenericEmptySearch("En mercado libre encontrarás lo que necesites")
            }

            // validacion para determinar si esta cargando resultados
            if(state.isLoading) {
                GenericLoading()
            }

            // validación para determinar si ha ocurrido algún error
            if (state.error.isNotBlank()) {
                GenericError(state.error)
            }

        }
    }

}