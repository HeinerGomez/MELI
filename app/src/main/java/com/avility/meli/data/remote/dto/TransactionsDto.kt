package com.avility.meli.data.remote.dto

data class TransactionsDto(
    val canceled: Int,
    val completed: Int,
    val period: String,
    val ratings: RatingsDto,
    val total: Int
)