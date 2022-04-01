package com.avility.meli.domain.usescases.products

import com.avility.meli.common.Constans
import com.avility.meli.common.Resource
import com.avility.meli.data.remote.dto.toProductItem
import com.avility.meli.domain.model.ProductItem
import com.avility.meli.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

/**
 * Author: Heiner Gómez <agheinerag@gmail.com>
 * Description: Caso de uso encargado de gestionar las busquedas de los productos
 */
class GetSearchProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    /**
     * para los casos de uso frecuento usar operator functions para evitar llamar el .invoke, y trabajo
     * con los flows para emitir valores y de esta forma gestionar mas facíl el flujo de datos.
     */
    operator fun invoke(query: String): Flow<Resource<List<ProductItem>>> = flow {
        try {
            emit(Resource.Loading<List<ProductItem>>())
            val searchResponseDto = repository.getResultForSearch(query, 0)
            val products = searchResponseDto.results.map { it.toProductItem() }
            emit(Resource.Success<List<ProductItem>>(products))
        } catch (e: HttpException) {
            emit(Resource.Error<List<ProductItem>>(Constans.GENERIC_ERROR))
        } catch (e: IOException) {
            emit(Resource.Error<List<ProductItem>>(Constans.NETWORK_ERROR))
        } catch (e: Exception) {
            emit(Resource.Error<List<ProductItem>>(Constans.GENERIC_ERROR))
        }
    }

}