package com.avility.meli.common

import java.text.NumberFormat
import java.util.*

/**
 * Author: Heiner Gómez <agheinerag@gmail.com>
 * Description: Archivo de utilidades para funcionalidades que sean globales y facilmente reutilizables
 */
object Utils {

    /**
     * Se encarga de transformar un entero a un valor en la moneda colombiana
     */
    fun transformToCurrency(number: Int): String {
        val formatCOP: NumberFormat = NumberFormat.getCurrencyInstance(Locale("es", "CO"))
        return formatCOP.format(number)
    }
}