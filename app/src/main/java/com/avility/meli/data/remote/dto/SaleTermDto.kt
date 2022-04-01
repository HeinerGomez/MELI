package com.avility.meli.data.remote.dto


import com.google.gson.annotations.SerializedName

data class SaleTermDto(
    val id: String,
    val name: String,
    @SerializedName("value_name")
    val valueName: String
)