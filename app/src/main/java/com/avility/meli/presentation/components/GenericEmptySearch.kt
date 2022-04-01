package com.avility.meli.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.avility.meli.R

/**
 * Author: Heiner Gómez <agheinerag@gmail.com>
 * Description: Se crea este componente para mostrar una imagen acompañado de una animación de lottie,
 * para los casos en donde no se haya iniciado una busqueda o dicha busqueda no de resultados
 */
@Composable
fun GenericEmptySearch(
    disclaimerMessage: String = "Busca algo"

) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty_search))
    val progress by animateLottieCompositionAsState(
        composition,
        isPlaying = true,
        iterations = 1,
        speed = 1f
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        Column() {
            LottieAnimation(
                modifier = Modifier.height(200.dp),
                composition = composition,
                progress = progress,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = disclaimerMessage,
                style = MaterialTheme.typography.subtitle1.copy(
                    color = Color(0xFFF5A623)
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}