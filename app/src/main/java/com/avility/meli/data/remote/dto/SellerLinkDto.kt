package com.avility.meli.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SellerLinkDto(
    @SerializedName("car_dealer")
    val carDealer: Boolean,
    val id: Int,
    val permalink: String?,
    @SerializedName("real_estate_agency")
    val realEstateAgency: Boolean,
    val tags: List<String>?
)