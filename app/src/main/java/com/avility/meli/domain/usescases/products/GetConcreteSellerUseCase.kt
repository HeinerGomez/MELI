package com.avility.meli.domain.usescases.products

import com.avility.meli.common.Constans
import com.avility.meli.common.Resource
import com.avility.meli.data.remote.dto.toSellerBasicInfo
import com.avility.meli.domain.model.SellerBasicInfo
import com.avility.meli.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

/**
 * Author: Heiner Gómez <agheinerag@gmail.com>
 * Description: Caso de uso encargado de gestionar la consulta de un vendedor de un producto en concreto
 */
class GetConcreteSellerUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {

    /**
     * para los casos de uso frecuento usar operator functions para evitar llamar el .invoke, y trabajo
     * con los flows para emitir valores y de esta forma gestionar mas facíl el flujo de datos.
     */
    operator fun invoke(sellerId: Int): Flow<Resource<SellerBasicInfo>> = flow {
        try {
            emit(Resource.Loading<SellerBasicInfo>())
            val responseSellerDto = productRepository.getConcreteSeller(sellerId)
            emit(Resource.Success<SellerBasicInfo>(responseSellerDto.toSellerBasicInfo()))
        } catch (e: HttpException) {
            emit(Resource.Error<SellerBasicInfo>(Constans.GENERIC_ERROR))
        } catch (e: IOException) {
            emit(Resource.Error<SellerBasicInfo>(Constans.NETWORK_ERROR))
        } catch (e: Exception) {
            emit(Resource.Error<SellerBasicInfo>(Constans.GENERIC_ERROR))
        }
    }
}