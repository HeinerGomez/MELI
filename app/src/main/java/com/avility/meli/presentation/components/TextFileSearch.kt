package com.avility.meli.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.*

/**
 * Author: Heiner Gómez <agheinerag@gmail.com>
 * Description: Se crea este componente para dejar un textField generico y que se pueda adaptar a varias
 * funcionalidades como por ejemplo la de un buscador.
 */
@ExperimentalUnitApi
@Composable
fun TextFieldSearch(
    modifier: Modifier = Modifier,
    text: String = "",
    hint: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    onTextChange: (value: String) -> Unit = {}
) {
    TextField(
        value = text,
        colors = TextFieldDefaults
            .textFieldColors(
                textColor = Color(0xffc2c0c5),
                backgroundColor = MaterialTheme.colors.background,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
        textStyle = LocalTextStyle.current.copy(fontSize = TextUnit(12f, TextUnitType.Sp)),
        onValueChange = onTextChange,
        placeholder = {
            Text(
                text = hint,
                color = Color(0xffc2c0c5),
                style = MaterialTheme.typography.caption.copy(fontSize = 12.sp)
            )
        },
        isError = false,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        singleLine = true,
        leadingIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "",
                    tint = Color(0xffc2c0c5)
                )
            }
        },
        shape = RoundedCornerShape(15.dp),
        modifier = modifier,

    )
}