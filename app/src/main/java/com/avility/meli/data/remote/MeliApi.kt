package com.avility.meli.data.remote

import com.avility.meli.common.Constans
import com.avility.meli.data.remote.dto.DetailProductDto
import com.avility.meli.data.remote.dto.SearchResponseDto
import com.avility.meli.data.remote.dto.SellerDto
import retrofit2.http.*

/**
 * Author: Heiner Gómez <agheinerag@gmail.com>
 * Description: Interface para la definición de los endpoints que se usarán para conectarse a la API de MELI
 */
interface MeliApi {

    /**
     * Se encarga de la busqueda de productos
     */
    @GET("sites/${Constans.DEFAULT_SITE_ID}/search")
    suspend fun getResultsOfSearch(
        @Query("q") query: String,
        @Query("offset") offset: Int
    ): SearchResponseDto

    /**
     * Se encarga de buscar un producto en concreto de una busqueda mas general
     */
    @GET("items/{item_id}")
    suspend fun getItemFromSearch(
        @Path("item_id") itemId: String
    ): DetailProductDto

    /**
     * Se encarga de obtener un vendedor en base a su id
     */
    @GET("users/{seller_id}")
    suspend fun getConcreteSeller(
        @Path("seller_id") sellerId: Int
    ): SellerDto
}