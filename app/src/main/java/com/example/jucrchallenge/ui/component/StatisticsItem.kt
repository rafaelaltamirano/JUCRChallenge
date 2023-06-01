package com.example.jucrchallenge.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jucrchallenge.R
import com.example.jucrchallenge.ui.theme.Primary


@Composable
fun StaticItem(item: Int) {
    Card(
        shape = RoundedCornerShape(10),
        elevation = 4.dp,
        modifier = Modifier
            .width(150.dp),

        border = BorderStroke(5.dp, Color.White),
        backgroundColor = Color.White
    ) {
        Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.Center) {
            Row(Modifier.padding(10.dp)) {
                Box(
                    modifier = Modifier.size(45.dp).clip(CircleShape).background(Primary.copy(alpha = 0.3f))
                        .padding(10.dp)
                )
                {
                    Image(
                        painter = painterResource(R.drawable.ic_battery_red),
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = "My Image",
                    )
                }
            }
            Column(Modifier.padding(10.dp)) {
                Text(
                    text = "240 Volt",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.alpha(0.6f),
                    text = "Voltage",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold

                )
            }
        }
    }

}