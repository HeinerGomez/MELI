package com.avility.meli.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PagingDto(
    val limit: Int,
    val offset: Int,
    @SerializedName("primary_results")
    val primaryResults: Int,
    val total: Int
)