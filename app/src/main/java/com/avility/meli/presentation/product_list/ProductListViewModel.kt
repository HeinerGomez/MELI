package com.avility.meli.presentation.product_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avility.meli.common.Resource
import com.avility.meli.domain.usescases.products.GetSearchProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Author: Heiner Gómez <agheinerag@gmail.com>
 * Description: view model asociado a la pantalla principal de busqueda de productos, desde aca llamo los casos
 * de uso para gestionar las busquedas de productos, y desde aca tambien altero el estado
 * para que posteriormente de forma reactiva se actualice la vista
 */
@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getSearchProductsUseCase: GetSearchProductsUseCase
) : ViewModel(){

    // estado para el listado de productos
    private val _state = mutableStateOf(ProductListState())
    val state: State<ProductListState> = _state

    // estado para manejar lo que se escribe en el TextField
    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    /**
     * Job importante para optimizar las consultas y que no se generen muchas peticiones al mismo tiempo
     */
    private var searchJob: Job? = null

    /**
     * Se encarga de ejecutar el caso de uso encargado de las busquedas
     */
    fun searchProduct(query: String) {
        _searchQuery.value = query

        /**
         * si es que ya hay una busqueda en proceso, realiza la cancelación del job hasta que el
         * usuario acabe de escribir lo que quiere buscar
         */
        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            // este delay es importante para darle tiempo a una posible cancelación del job
            delay(500L)

            if (query.isNotBlank()) {

                withContext(Dispatchers.IO) {
                    getSearchProductsUseCase(query).onEach { result ->
                        when (result) {
                            is Resource.Success -> {
                                _state.value = state.value.copy(
                                    products = result.data ?: emptyList(),
                                    isLoading = false
                                )
                            }
                            is Resource.Error -> {
                                _state.value = state.value.copy(
                                    products = emptyList(),
                                    isLoading = false,
                                    error = result.message ?: "Error Desconocido"
                                )
                            }
                            is Resource.Loading -> {
                                _state.value = state.value.copy(
                                    products = emptyList(),
                                    isLoading = true,
                                    error = ""
                                )
                            }
                        }
                    }.launchIn(this)
                }
            } else {
                _state.value = state.value.copy(
                    products = emptyList(),
                    isLoading = false,
                    error = ""
                )
            }
        }
    }
}