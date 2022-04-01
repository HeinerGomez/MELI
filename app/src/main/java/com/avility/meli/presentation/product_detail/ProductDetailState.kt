package com.avility.meli.presentation.product_detail

import com.avility.meli.domain.model.ProductDetail

/**
 * Se encargará de manejar el estado del detalle del producto
 */
data class ProductDetailState (
    val isLoading: Boolean = false,
    val product: ProductDetail? = null,
    val error: String = ""
)