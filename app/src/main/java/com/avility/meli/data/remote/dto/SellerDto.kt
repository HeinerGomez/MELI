package com.avility.meli.data.remote.dto

import com.avility.meli.domain.model.LevelStatus
import com.avility.meli.domain.model.SellerBasicInfo
import com.google.gson.annotations.SerializedName

data class SellerDto(
    val id: Int,
    val nickname: String,
    val permalink: String,
    @SerializedName("registration_date")
    val registrationDate: String,
    @SerializedName("country_id")
    val countryId: String,
    @SerializedName("seller_reputation")
    val sellerReputation: SellerReputationDto?,
    val tags: List<String>
)

fun SellerDto.toSellerBasicInfo(): SellerBasicInfo {

    val levelStatus = when(sellerReputation?.levelId) {
        "5_green" -> LevelStatus.Green(sellerReputation.levelId)
        "4_light_green" -> LevelStatus.LightGreen(sellerReputation.levelId)
        "3_yellow" -> LevelStatus.Yellow(sellerReputation.levelId)
        "2_orange" -> LevelStatus.Orange(sellerReputation.levelId)
        "1_red" -> LevelStatus.Red(sellerReputation.levelId)
        else -> LevelStatus.Yellow("Indefinido")
    }

    return SellerBasicInfo(
        id,
        nickname,
        levelStatus,
        sellerReputation?.powerSellerStatus ?: "Desconocido"
    )
}