package com.example.jucrchallenge.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.example.jucrchallenge.domain.entities.Car
import com.example.jucrchallenge.domain.enum.Escale.*
import com.example.jucrchallenge.ui.theme.Danger
import com.example.jucrchallenge.ui.theme.Success
import com.example.jucrchallenge.ui.theme.Warning


@Composable
fun LazyItemsRow(car: Car?) {
    val carList = car?.statistics ?: emptyList()


    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(carList) {
            val value: Int
            val escale: String
            val icon: Int
            val color: Color

            when (it.scale) {
                VOLTAGE -> {
                    value = R.string.voltage
                    escale = stringResource(R.string.volt)
                    icon = R.drawable.ic_car_battery_solid
                    color = Danger
                }
                REMAINING_CHARGE -> {
                    value = R.string.remaining_charge
                    escale = stringResource(R.string.km)
                    icon = R.drawable.ic_battery_three_quarters_solid
                    color = Success
                }
                FAST_CHARGE -> {
                    value = R.string.fast_charge
                    escale = stringResource(R.string.min)
                    icon = R.drawable.ic_plug_circle_bolt_solid
                    color = Warning
                }
            }

            StaticItem(
                title ="${it.quantity} $escale",
                subtitle = stringResource(value),
                icon = icon,
                color = color
            )
        }
    }
}

@Composable
fun StaticItem(title: String, subtitle: String, icon: Int, color: Color) {

    Card(
        shape = RoundedCornerShape(10), elevation = 4.dp, modifier = Modifier
            .width(150.dp)
            .height(155.dp),

        border = BorderStroke(5.dp, Color.White), backgroundColor = Color.White
    ) {
        Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.Center) {
            Row(Modifier.padding(10.dp)) {
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .background(color.copy(alpha = 0.3f))
                        .padding(10.dp)
                ) {
                    Icon(
                        painter = painterResource(icon),
                        modifier = Modifier.fillMaxSize(),
                        tint = color,
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