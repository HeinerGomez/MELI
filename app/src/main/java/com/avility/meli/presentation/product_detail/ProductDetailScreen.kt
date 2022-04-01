package com.avility.meli.presentation.product_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.avility.meli.common.Utils
import com.avility.meli.domain.model.LevelStatus
import com.avility.meli.domain.model.ProductDetail
import com.avility.meli.domain.model.SellerBasicInfo
import com.avility.meli.presentation.components.GenericError
import com.avility.meli.presentation.components.GenericLoading
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

/**
 * Pantalla del detalle del producto dividida en 3 partes:
 * TopAppBar: ubicado en la parte de arriba para dejar el back button posicionado en la parte superior
 *            izquierda.
 * BodyContent: contenido principal en donde se muestra la información del producto incluida sus imagenes
 * SellerInformationSection: sección final en donde se muestra la información del vendedor
 */
@OptIn(ExperimentalUnitApi::class)
@Composable
fun ProductDetailScreen(
    navController: NavController,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {

    val productDetailState = viewModel.productDetailState.value
    val sellerState = viewModel.productSellerState.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TopAppBar(
                title = {
                    Text(text = "")
                },
                backgroundColor = Color(0xfffff158),
                contentColor = Color.Gray,
                elevation = 0.dp,
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
            LazyColumn(modifier = Modifier.padding(20.dp)) {
                item {

                    // procesa el cuerpo del detalle del producto
                    if (productDetailState.product != null) {
                        BodyContent(productDetailState.product)
                    }

                    // muestra loading si se esta procesando la peticion
                    if (productDetailState.isLoading) {
                        GenericLoading()
                    }

                    // muestra una alerta de error en caso de que algo saliera mal
                    if (productDetailState.error.isNotBlank()) {
                        GenericError(productDetailState.error)
                    }

                    // division entre la sección del body y la seccion de la información del vendedor
                    Spacer(modifier = Modifier.height(15.dp))
                    Divider(modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.height(15.dp))

                    // procesa la sección del vendedor
                    if (sellerState.seller != null && productDetailState.product != null) {
                        SellerInformationSection(sellerState.seller, productDetailState.product)
                    }
                }
            }
        }
    }
}

/**
 * Construye la visra del cuerpo del contenido del detalle del producto.
 */
@OptIn(ExperimentalPagerApi::class)
@ExperimentalUnitApi
@Composable
fun BodyContent(
    productDetail: ProductDetail
) {
    Spacer(modifier = Modifier.height(10.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(15.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = productDetail.condition.value,
            style = MaterialTheme.typography.caption.copy(
                fontSize = TextUnit(10f, TextUnitType.Sp),
                color = Color(0xFFA4A2A2)
            ), modifier = Modifier.padding(horizontal = 5.dp)
        )
        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
        Text(
            text = "${productDetail.availableQuantity} Disponible(s)", style = MaterialTheme.typography.caption.copy(
                fontSize = TextUnit(10f, TextUnitType.Sp),
                color = Color(0xFFA4A2A2)
            ), modifier = Modifier.padding(horizontal = 5.dp)
        )
    }
    Spacer(
        modifier = Modifier
            .height(5.dp)
            .padding(5.dp)
    )
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = productDetail.name,
            style = MaterialTheme.typography.body1
        )
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        HorizontalPager(productDetail.pictures.size) { currentPage ->
            Image(
                painter = rememberImagePainter(
                    data = productDetail.pictures[currentPage].url
                ),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Inside
            )
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(text = Utils.transformToCurrency(productDetail.price), style = MaterialTheme.typography.h5)
    }
}

/**
 * Para la construcción de la información del vendedor
 */
@Composable
fun SellerInformationSection(
    sellerBasicInfo: SellerBasicInfo,
    productDetail: ProductDetail
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Información sobre el vendedor", style = MaterialTheme.typography.subtitle1)
    }
    Spacer(modifier = Modifier.height(10.dp))
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .padding(5.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Room,
                contentDescription = null,
                tint = Color(0xFFA4A2A2)
            )
        }
        Column(Modifier.fillMaxWidth()) {
            Text(text = productDetail.sellerBasicLocation.city, style = MaterialTheme.typography.subtitle2)
            Text(
                text = sellerBasicInfo.name, style = MaterialTheme.typography.caption.copy(
                    color = when (sellerBasicInfo.levelStatus.status) {
                        "5_green" -> Color(0xFF00a650)
                        "4_light_green" -> Color(0xFF31E751)
                        "3_yellow" -> Color(0xfffff158)
                        "2_orange" -> Color(0xFFFF7733)
                        "1_red" -> Color(0xFFDD0808)
                        else -> {
                            Color(0xFFFF7733)
                        }
                    }
                )
            )
        }
    }
    Spacer(modifier = Modifier.height(5.dp))
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .padding(5.dp)
        ) {
            Icon(
                imageVector = Icons.Default.MilitaryTech,
                contentDescription = null,
                tint = when (sellerBasicInfo.levelStatus.status) {
                    "5_green" -> Color(0xFF00a650)
                    "4_light_green" -> Color(0xFF31E751)
                    "3_yellow" -> Color(0xfffff158)
                    "2_orange" -> Color(0xFFFF7733)
                    "1_red" -> Color(0xFFDD0808)
                    else -> {
                        Color(0xFFFF7733)
                    }
                }
            )
        }
        Column(Modifier.fillMaxWidth()) {
            Text(
                text = sellerBasicInfo.powerSellerStatus,
                style = MaterialTheme.typography.subtitle2.copy(
                    color = when (sellerBasicInfo.levelStatus.status) {
                        "5_green" -> Color(0xFF00a650)
                        "4_light_green" -> Color(0xFF31E751)
                        "3_yellow" -> Color(0xfffff158)
                        "2_orange" -> Color(0xFFFF7733)
                        "1_red" -> Color(0xFFDD0808)
                        else -> {
                            Color(0xFFFF7733)
                        }
                    }
                )
            )
        }
    }
}