package com.avility.meli.data.remote.dto

import com.avility.meli.domain.model.PictureItem
import com.google.gson.annotations.SerializedName

data class PictureDto(
    val id: String,
    @SerializedName("max_size")
    val maxSize: String,
    val quality: String,
    @SerializedName("secure_url")
    val secureUrl: String,
    val size: String,
    val url: String
)

fun PictureDto.toPictureItem(): PictureItem {
    return PictureItem(
        id,
        url
    )
}