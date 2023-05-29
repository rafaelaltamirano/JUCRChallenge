package com.example.jucrchallenge.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun StaticItem(item: Int) {
    Card(
        shape = RoundedCornerShape(100),
        modifier = Modifier
            .width(90.dp)
            .height(50.dp)
            .clip(CircleShape),
        border = BorderStroke(5.dp, Color.White),
        backgroundColor = Color.Red
    ) {
        Text(item.toString())
    }

}