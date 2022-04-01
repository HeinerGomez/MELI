package com.avility.meli.domain.usescases.products

import com.avility.meli.CoroutinesTestRule
import com.avility.meli.common.Resource
import com.avility.meli.data.repository.ProductFakeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetSearchProductsUseCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var productFakeRepository: ProductFakeRepository
    private lateinit var getSearchProductsUseCase: GetSearchProductsUseCase

    /**
     * Configuramos el caso de uso con un fake repository ya que no podemos pasar el api como parametro
     */
    @Before
    fun setup() {
        productFakeRepository = ProductFakeRepository()
        getSearchProductsUseCase = GetSearchProductsUseCase(productFakeRepository)
    }

    /**
     * Una respuesta se concidera que se resolvio correctamente cuando su sealed class retorna
     * Resource.Success
     */
    @Test
    fun `valida que la data no sea nula cuando la respuesta del caso de uso se resolvio correctamente`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            getSearchProductsUseCase("ipho").collect {
                if (it is Resource.Success) {
                    val data = it.data
                    assert(data != null)
                }
            }
        }

    /**
     * Despues de que la respuesta sea Success, se deberá validar que la data no llegue con una lista vacia
     */
    @Test
    fun `valida que la data no este vacia para un termino de busqueda iphone`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            getSearchProductsUseCase("iphone").collect {
                if (it is Resource.Success) {
                    val data = it.data

                    if (data != null) {
                        assert(data.isNotEmpty())
                    } else {
                        assert(false) // falla porque no debería devolver nulo
                    }
                }
            }
        }

    /**
     * entiendase el estado cargando como un retorno del recurso: Resource.Loading, que indica precisamente
     * que la petición se esta procesando
     */
    @Test
    fun `una vez se llame el caso de uso, este debera notificar que la peticion se esta procesando`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            getSearchProductsUseCase("iphone").collectIndexed { index, value ->
                if (index == 0) {
                    assert(value is Resource.Loading)
                }
            }
        }

}