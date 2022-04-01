package com.avility.meli.presentation.product_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.avility.meli.common.Utils
import com.avility.meli.domain.model.ConditionProduct
import com.avility.meli.domain.model.ProductItem
import com.avility.meli.presentation.components.BadgeTag

/**
 * Componente que reutiliza cada item de los productos resultantes de una busqueda
 */
@Composable
fun ProductItem(
    productItem: ProductItem,
    onItemClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick()
            }
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
        ) {
            Image(
                painter = rememberImagePainter(
                    data = productItem.imagePath
                ),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = productItem.name,
                style = MaterialTheme.typography.body2.copy(
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Medium
                )
            )
            Text(
                text = Utils.transformToCurrency(productItem.price),
                style = MaterialTheme.typography.body2
            )
            BadgeTag(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(
                        if (productItem.conditionProduct is ConditionProduct.New) {
                            Color(0xFFB3E7B5)
                        } else {
                            Color(0xFFFFAB91)
                        }
                    ),
                value = productItem.conditionProduct.value,
                textStyle = MaterialTheme.typography.caption.copy(
                    color = if (productItem.conditionProduct is ConditionProduct.New) {
                        Color(0xFF00a650)
                    } else {
                        Color(0xFFFF7733)
                    }
                )
            )
        }
    }
}