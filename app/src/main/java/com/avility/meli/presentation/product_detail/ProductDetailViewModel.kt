package com.avility.meli.presentation.product_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avility.meli.common.Constans
import com.avility.meli.common.Resource
import com.avility.meli.domain.usescases.products.GetConcreteSellerUseCase
import com.avility.meli.domain.usescases.products.GetDetailProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Author: Heiner Gómez <agheinerag@gmail.com>
 * Description: view model asociado a la pantalla del detalle del producto, desde aca llamo los casos
 * de uso para obtener la información del producto y del vendedor, y desde aca tambien altero el estado
 * para que posteriormente de forma reactiva se actualice la vista
 */
@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getDetailProductUseCase: GetDetailProductUseCase,
    private val getConcreteSellerUseCase: GetConcreteSellerUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    // para manejar el estado del detalle del producto
    private val _productDetailState = mutableStateOf(ProductDetailState())
    val productDetailState: State<ProductDetailState> = _productDetailState

    // para manejar el estado del vendedor
    private val _productSellerState = mutableStateOf(ProductSellerState())
    val productSellerState: State<ProductSellerState> = _productSellerState

    /**
     * una vez se acceda a la pantalla del detalle del producto se recupera el parametro que viaja
     * por la navegación para obtener el detalle del producto
     */
    init {
        savedStateHandle.get<String>(Constans.PARAM_PRODUCT_ID)?.let { productId ->
            Log.d("Heiner", "productId: $productId")
            getProduct(productId)
        }
    }

    /**
     * Se encarga de obtener el detalle de un producto en base a su id y modificar el estado de la
     * aplicación para que la vista reaccione de forma reactiva
     */
    private fun getProduct(productId: String) {
        getDetailProductUseCase(productId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _productDetailState.value = productDetailState.value.copy(
                        product = result.data,
                        isLoading = false
                    )

                    if (result.data != null) {
                        // una vez que obtenga el detalle del producto intento obtener la info del vendedor
                        getConcreteSeller(result.data.sellerId)
                    }

                }
                is Resource.Error -> {
                    _productDetailState.value = productDetailState.value.copy(
                        product = null,
                        isLoading = false,
                        error = result.message ?: "Error inesperado"
                    )
                }
                is Resource.Loading -> {
                    _productDetailState.value = productDetailState.value.copy(
                        product = null,
                        isLoading = true,
                        error = ""
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    /**
     * Se encarga de obtener la información de un vendedor de un producto en concreto, también modifica
     * el estado de la aplicación para que la vista reacione de forma reactiva
     */
    private fun getConcreteSeller(sellerId: Int) {
        getConcreteSellerUseCase(sellerId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _productSellerState.value = productSellerState.value.copy(
                        seller = result.data,
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _productSellerState.value = productSellerState.value.copy(
                        seller = null,
                        isLoading = false,
                        error = result.message ?: "Error inesperado"
                    )
                }
                is Resource.Success -> {
                    _productSellerState.value = productSellerState.value.copy(
                        seller = null,
                        isLoading = true,
                        error = ""
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}