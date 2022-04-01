package com.avility.meli.presentation.product_detail

import com.avility.meli.domain.model.SellerBasicInfo

/**
 * Se encargará de manejar el estado de un vendedor de un producto en concreto
 */
data class ProductSellerState (
    val isLoading: Boolean = false,
    val seller: SellerBasicInfo? = null,
    val error: String = ""
)