package com.example.jucrchallenge.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jucrchallenge.R

@Composable
fun StatisticsTopBar( title: String,
                      indicatorText :String? = null ) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
        Text(
            text = title,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        Spacer(
            Modifier
                .weight(1f)
                .fillMaxHeight()
        )
        if(indicatorText.isNullOrEmpty()) {
            Icon(
                painter = painterResource(R.drawable.ic_three_dots),
                modifier = Modifier.size((25.dp)).padding(2.dp).alpha(0.6f),
                contentDescription = "Three dots menu",
            )
        }
        else {
            Text(
                modifier = Modifier.alpha(0.6f),
                text = indicatorText,
                color = Color.Black,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}