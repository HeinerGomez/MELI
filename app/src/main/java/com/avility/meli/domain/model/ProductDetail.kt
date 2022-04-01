package com.avility.meli.domain.model

data class ProductDetail (
    val id: String,
    val name: String,
    val price: Int,
    val condition: ConditionProduct,
    val soldQuantity: Int,
    val pictures: List<PictureItem>,
    val availableQuantity: Int,
    val attributeItems: List<AttributeItem>,
    val sellerId: Int,
    val sellerBasicLocation: SellerBasicLocation
)