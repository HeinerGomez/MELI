package com.avility.meli.data.repository

import com.avility.meli.data.remote.MeliApi
import com.avility.meli.data.remote.dto.DetailProductDto
import com.avility.meli.data.remote.dto.SearchResponseDto
import com.avility.meli.data.remote.dto.SellerDto
import com.avility.meli.domain.repository.ProductRepository
import javax.inject.Inject

/**
 * Author: Heiner Gómez <agheinerag@gmail.com>
 * Description: repositorio que hace de puente con el api service
 */
class ProductRepositoryImpl @Inject constructor(
    private val api: MeliApi
) : ProductRepository {

    /**
     * Para las busquedas de productos
     */
    override suspend fun getResultForSearch(query: String, offset: Int): SearchResponseDto {
        return api.getResultsOfSearch(query, offset)
    }

    /**
     * Para obtener el detalle de un producto seleccionado
     */
    override suspend fun getItemFromSearch(itemId: String): DetailProductDto {
        return api.getItemFromSearch(itemId)
    }

    /**
     * Para obtener el vendedor de un producto en concreto
     */
    override suspend fun getConcreteSeller(sellerId: Int): SellerDto {
        return api.getConcreteSeller(sellerId)
    }
}