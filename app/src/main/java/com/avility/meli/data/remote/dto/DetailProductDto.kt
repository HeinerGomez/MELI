package com.avility.meli.data.remote.dto


import com.avility.meli.domain.model.ConditionProduct
import com.avility.meli.domain.model.PictureItem
import com.avility.meli.domain.model.ProductDetail
import com.google.gson.annotations.SerializedName

data class DetailProductDto(
    @SerializedName("accepts_mercadopago")
    val acceptsMercadoPago: Boolean,
    val attributes: List<AttributeDto>,
    @SerializedName("automatic_relist")
    val automaticRelist: Boolean,
    @SerializedName("available_quantity")
    val availableQuantity: Int,
    @SerializedName("base_price")
    val basePrice: Int,
    @SerializedName("buying_mode")
    val buyingMode: String,
    @SerializedName("catalog_listing")
    val catalogListing: Boolean,
    @SerializedName("catalog_product_id")
    val catalogProductId: String,
    @SerializedName("category_id")
    val categoryId: String,
    @SerializedName("channels")
    val channels: List<String>,
    @SerializedName("condition")
    val condition: String,
    @SerializedName("currency_id")
    val currencyId: String,
    @SerializedName("date_created")
    val dateCreated: String,
    @SerializedName("deal_ids")
    val dealIds: List<String>,
    @SerializedName("domain_id")
    val domainId: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("initial_quantity")
    val initialQuantity: Int,
    @SerializedName("international_delivery_mode")
    val internationalDeliveryMode: String,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("listing_source")
    val listingSource: String,
    @SerializedName("listing_type_id")
    val listingTypeId: String,
    @SerializedName("original_price")
    val originalPrice: Int,
    @SerializedName("permalink")
    val permalink: String,
    @SerializedName("pictures")
    val pictures: List<PictureDto>,
    val price: Int,
    @SerializedName("sale_terms")
    val saleTerms: List<SaleTermDto>,
    @SerializedName("secure_thumbnail")
    val secureThumbnail: String,
    @SerializedName("seller_address")
    val sellerAddress: SellerAddressDto,
    @SerializedName("seller_id")
    val sellerId: Int,
    @SerializedName("site_id")
    val siteId: String,
    @SerializedName("sold_quantity")
    val soldQuantity: Int,
    @SerializedName("start_time")
    val startTime: String,
    val status: String,
    @SerializedName("stop_time")
    val stopTime: String,
    val tags: List<String>,
    val thumbnail: String,
    @SerializedName("thumbnail_id")
    val thumbnailId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("warranty")
    val warranty: String
)

fun DetailProductDto.toProductDetail(): ProductDetail {

    val conditionProduct = if (condition == "new") {
        ConditionProduct.New("Nuevo")
    } else {
        ConditionProduct.Used("Usado")
    }

    val picturesItem = pictures.map { it.toPictureItem() }

    val attributesItem = attributes.map { it.toAttributeItem() }

    val sellerBasicLocation = sellerAddress.toSellerBasicLocation()

    return ProductDetail(
        id,
        title,
        price,
        conditionProduct,
        soldQuantity,
        picturesItem,
        availableQuantity,
        attributesItem,
        sellerId,
        sellerBasicLocation
    )
}