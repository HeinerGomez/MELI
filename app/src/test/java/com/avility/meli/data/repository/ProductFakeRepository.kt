package com.avility.meli.data.repository

import com.avility.meli.data.remote.dto.DetailProductDto
import com.avility.meli.data.remote.dto.SearchResponseDto
import com.avility.meli.data.remote.dto.SellerDto
import com.avility.meli.domain.repository.ProductRepository
import com.google.gson.Gson

/**
 * Para simular respuestas del servidor
 */
class ProductFakeRepository : ProductRepository {

    private val gson = Gson()

    override suspend fun getResultForSearch(query: String, offset: Int): SearchResponseDto {
        return gson.fromJson(
            this::class.java.classLoader.getResource("exampleProducts.json").readText(),
            SearchResponseDto::class.java
        )
    }

    override suspend fun getItemFromSearch(itemId: String): DetailProductDto {
        return gson.fromJson(
            this::class.java.classLoader.getResource("exampleDetailProduct.json").readText(),
            DetailProductDto::class.java
        )
    }

    override suspend fun getConcreteSeller(sellerId: Int): SellerDto {
        return gson.fromJson(
            this::class.java.classLoader.getResource("exampleSeller.json").readText(),
            SellerDto::class.java
        )
    }
}