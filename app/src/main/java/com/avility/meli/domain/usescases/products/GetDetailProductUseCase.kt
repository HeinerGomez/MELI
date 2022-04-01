package com.avility.meli.domain.usescases.products

import com.avility.meli.common.Constans
import com.avility.meli.common.Resource
import com.avility.meli.data.remote.dto.toProductDetail
import com.avility.meli.domain.model.ProductDetail
import com.avility.meli.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

/**
 * Author: Heiner Gómez <agheinerag@gmail.com>
 * Description: Caso de uso encargado de gestionar la consulta del detalle de un producto seleccionado
 */
class GetDetailProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    /**
     * para los casos de uso frecuento usar operator functions para evitar llamar el .invoke, y trabajo
     * con los flows para emitir valores y de esta forma gestionar mas facíl el flujo de datos.
     */
    operator fun invoke(itemId: String): Flow<Resource<ProductDetail>> = flow {
        try {
            emit(Resource.Loading<ProductDetail>())
            val responseDetailProductDto = repository.getItemFromSearch(itemId)
            emit(Resource.Success<ProductDetail>(responseDetailProductDto.toProductDetail()))
        } catch (e: HttpException) {
            emit(Resource.Error<ProductDetail>(Constans.GENERIC_ERROR))
        } catch (e: IOException) {
            emit(Resource.Error<ProductDetail>(Constans.NETWORK_ERROR))
        } catch (e: Exception) {
            emit(Resource.Error<ProductDetail>(Constans.GENERIC_ERROR))
        }
    }

}