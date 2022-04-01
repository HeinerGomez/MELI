package com.avility.meli.data.remote.dto

import com.avility.meli.domain.model.ConditionProduct
import com.avility.meli.domain.model.ProductItem
import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("accepts_mercadopago")
    val acceptsMercadoPago: Boolean,
    val address: AddressDto,
    val attributes: List<AttributeDto>,
    @SerializedName("available_quantity")
    val availableQuantity: Int,
    @SerializedName("buying_mode")
    val buyingMode: String,
    @SerializedName("catalog_listing")
    val catalogListing: Boolean,
    @SerializedName("catalog_product_id")
    val catalogProductId: String,
    @SerializedName("category_id")
    val categoryId: String,
    val condition: String,
    @SerializedName("currency_id")
    val currencyId: String,
    @SerializedName("domain_id")
    val domainId: String,
    val id: String,
    @SerializedName("listing_type_id")
    val listingTypeId: String,
    @SerializedName("order_backend")
    val orderBackend: Int,
    @SerializedName("original_price")
    val originalPrice: Int,
    val permalink: String,
    val price: Int,
    val seller: SellerLinkDto,
    @SerializedName("site_id")
    val siteId: String,
    @SerializedName("sold_quantity")
    val soldQuantity: Int,
    @SerializedName("stop_time")
    val stopTime: String,
    val tags: List<String>,
    val thumbnail: String,
    @SerializedName("thumbnail_id")
    val thumbnailId: String,
    val title: String,
    @SerializedName("use_thumbnail_id")
    val useThumbnailId: Boolean
)

fun ProductDto.toProductItem(): ProductItem {
    val conditionProduct = if (condition == "new") {
        ConditionProduct.New("Nuevo")
    } else {
       ConditionProduct.Used("Usado")
    }
    return ProductItem(
        id,
        title,
        price,
        availableQuantity,
        conditionProduct,
        thumbnail
    )
}