package com.avility.meli.domain.repository

import com.avility.meli.data.remote.dto.DetailProductDto
import com.avility.meli.data.remote.dto.SearchResponseDto
import com.avility.meli.data.remote.dto.SellerDto

/**
 * Author: Heiner Gómez <agheinerag@gmail.com>
 * Description: Se crea la interfaz del repository por varios motivos:
 * 1. para hacer uso del principio de inversion de dependencias, esto con el objetivo de que la capa domain,
 *    no conozca la logica y/o funcionamiento de la capa data.
 * 2. para poder crear fake repositories y de esta forma facilitar las pruebas unitarias
 */
interface ProductRepository {

    suspend fun getResultForSearch(query: String, offset: Int): SearchResponseDto

    suspend fun getItemFromSearch(itemId: String): DetailProductDto

    suspend fun getConcreteSeller(sellerId: Int): SellerDto
}