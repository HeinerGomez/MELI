package com.avility.meli.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

/**
 * Author: Heiner Gómez <agheinerag@gmail.com>
 * Description: Se crea este componente con el fin de crear un badge personalizado el cual se usará para
 * mostrar el estado de un producto, si es nuevo o usado.
 */
@Composable
fun BadgeTag(
    modifier: Modifier,
    value: String,
    textStyle: TextStyle
) {
    Box(modifier = modifier) {
        Text(
            value,
            style = textStyle,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(2.dp)
        )
    }
}