package com.avility.meli.common

/**
 * Author: Heiner Gómez <agheinerag@gmail.com>
 * Description: Sealed class enfocada en representar los 3 posibles estados de una petición
 * Success -> representa que la petición ya se ejecuto y fue exitosa
 * Error -> representa que la petición fallo ya sea en su respuesta o en un proceso anterior o posterior
 *          a su ejecución
 * Loading -> representa que la petición esta en proceso y que aun no se ha resuelto.
 */
sealed class Resource<T>(val data:T? = null, val message: String? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
    class Loading<T>(data:T? = null): Resource<T>(data)
}