package com.avility.meli.data.remote.dto

import com.avility.meli.domain.model.AttributeItem
import com.google.gson.annotations.SerializedName

data class AttributeDto(
    @SerializedName("attribute_group_id")
    val attributeGroupId: String,
    @SerializedName("attribute_group_name")
    val attributeGroupName: String,
    val id: String,
    val name: String,
    val source: Long,
    @SerializedName("value_id")
    val valueId: String?,
    @SerializedName("value_name")
    val valueName: String?,
)

fun AttributeDto.toAttributeItem(): AttributeItem {
    return AttributeItem(
        id,
        name,
        valueName ?: ""
    )
}