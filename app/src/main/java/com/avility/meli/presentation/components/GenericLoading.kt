package com.avility.meli.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.*
import com.avility.meli.R

/**
 * Author: Heiner Gómez <agheinerag@gmail.com>
 * Description: Se crea este componente para mostrar un loading animado con la libreria lottie, para
 * los casos en donde se este procesando una peticion
 */
@Composable
fun GenericLoading() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    val progress by animateLottieCompositionAsState(
        composition,
        isPlaying = true,
        iterations = LottieConstants.IterateForever,
        speed = 1.5f
    )

    LottieAnimation(
        composition,
        progress,
    )
}