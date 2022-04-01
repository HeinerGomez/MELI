package com.avility.meli.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SearchResponseDto(
    val paging: PagingDto,
    val query: String,
    val results: List<ProductDto>,
    @SerializedName("site_id")
    val siteId: String,
)

