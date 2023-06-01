package com.example.jucrchallenge.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jucrchallenge.R
import com.example.jucrchallenge.ui.theme.GrayLight


fun LazyListScope.nearbyPointsList() {
    items(20) {
        Card(
            shape = RoundedCornerShape(10),
            elevation = 0.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .padding(bottom = 8.dp),

            border = BorderStroke(5.dp, Color.White),
            backgroundColor = GrayLight
        ) {
            Row(
                modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.padding(10.dp)) {
                    Text(
                        text = "Ranchview Dr. Richardison",
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        modifier = Modifier.alpha(0.6f),
                        text = "4/10 Available",
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold

                    )
                }
                Spacer(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
                Column(
                    Modifier
                        .padding(10.dp)
                        .alpha(0.4f)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_location_haert),
                        modifier = Modifier.size((30.dp)).padding(2.dp),
                        contentDescription = "My Image",
                    )
                    Text(
                        modifier = Modifier,
                        text = "1.2 km",
                        color = Color.Black,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}