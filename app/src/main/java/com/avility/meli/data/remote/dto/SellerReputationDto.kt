package com.avility.meli.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SellerReputationDto(
    @SerializedName("level_id")
    val levelId: String?,
    val metrics: MetricsDto?,
    @SerializedName("power_seller_status")
    val powerSellerStatus: String?,
    val transactions: TransactionsDto?
)