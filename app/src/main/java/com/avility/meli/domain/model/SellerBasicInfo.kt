package com.avility.meli.domain.model


sealed class LevelStatus(val status: String) {
    class Green(status: String) : LevelStatus(status)
    class LightGreen(status: String) : LevelStatus(status)
    class Yellow(status: String) : LevelStatus(status)
    class Orange(status: String) : LevelStatus(status)
    class Red(status: String) : LevelStatus(status)
}

data class SellerBasicInfo(
    val id: Int,
    val name: String,
    val levelStatus: LevelStatus,
    val powerSellerStatus: String
)

