package com.avility.meli.presentation.product_list

import com.avility.meli.domain.model.ProductItem

/**
 * Se encargará de gestionar el estado del listado de productos resultantes de una busqueda
 */
data class ProductListState (
    val isLoading: Boolean = false,
    val products: List<ProductItem> = emptyList(),
    val error: String = ""
)