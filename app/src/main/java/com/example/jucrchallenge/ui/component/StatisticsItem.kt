package com.example.jucrchallenge.ui.component

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.jucrchallenge.R
import com.example.jucrchallenge.ui.component.iconStatistics.*
import com.example.jucrchallenge.ui.theme.Primary

enum class iconStatistics {
    VOLTAGE, REMAINING_CHARGE, FAST_CHARGE
}

@Composable
fun LazyItemsRow() {

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(200, key = { it }) {
            StaticItem(
                stringResource(R.string.n_volt, "240"),
                stringResource(R.string.voltage),
                icon = VOLTAGE
            )
        }
    }
}

@Composable
fun StaticItem(title: String, subtitle: String, icon: iconStatistics) {

    val iconRes = when (icon) {
        VOLTAGE -> R.drawable.ic_car_battery_solid
        REMAINING_CHARGE -> R.drawable.ic_battery_three_quarters_solid
        FAST_CHARGE -> R.drawable.ic_plug_circle_bolt_solid
    }

    Card(
        shape = RoundedCornerShape(10), elevation = 4.dp, modifier = Modifier.width(150.dp),

        border = BorderStroke(5.dp, Color.White), backgroundColor = Color.White
    ) {
        Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.Center) {
            Row(Modifier.padding(10.dp)) {
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .background(Primary.copy(alpha = 0.3f))
                        .padding(10.dp)
                ) {
                    Icon(
                        painter = painterResource(iconRes),
                        modifier = Modifier.fillMaxSize(),
                        tint = Primary,
                        contentDescription = "Charge status",
                    )
                }
            }
            Column(Modifier.padding(10.dp)) {
                Text(
                    text = title,
                    color = Color.Black,
                    style = MaterialTheme.typography.h3,
                )
                Text(
                    modifier = Modifier.alpha(0.6f),
                    text = subtitle,
                    color = Color.Black,
                    style = MaterialTheme.typography.caption,
                )
            }
        }
    }

}