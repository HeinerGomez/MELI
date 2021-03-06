package com.avility.meli.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AddressDto(

    @SerializedName("city_id")
    val cityId: String,
    @SerializedName("city_name")
    val cityName: String,
    @SerializedName("state_id")
    val stateId: String,
    @SerializedName("state_name")
    val stateName: String
)