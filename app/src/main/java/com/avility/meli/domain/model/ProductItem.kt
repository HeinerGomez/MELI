package com.avility.meli.domain.model

sealed class ConditionProduct(val value: String) {
    class New(value: String) : ConditionProduct(value)
    class Used(value: String) : ConditionProduct(value)
}

data class ProductItem (
    val id: String,
    val name: String,
    val price: Int,
    val availableQuantity: Int,
    val conditionProduct: ConditionProduct,
    val imagePath: String
)