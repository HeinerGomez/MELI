package com.avility.meli.presentation.splash_screen

import android.util.Log
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.avility.meli.R
import com.avility.meli.presentation.util.Screen
import kotlinx.coroutines.delay

/**
 * Pantalla para simular un splash screen
 */
@Composable
fun SplashScreen(navController: NavController) {

    Log.d("Heiner: ", "SplashScreen: todo bien hasta aqui 0")

    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.5f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        Log.d("Heiner: ", "SplashScreen: todo bien hasta aqui 1")
        navController.navigate(Screen.SearchProductScreen.route)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xfffff158)),
    ) {
        Log.d("Heiner: ", "SplashScreen: todo bien hasta aqui 3")

        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Meli Logo",
            modifier = Modifier.scale(scale.value),
        )
    }
}