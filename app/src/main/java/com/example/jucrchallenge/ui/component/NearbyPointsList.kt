package com.example.jucrchallenge.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.jucrchallenge.R
import com.example.jucrchallenge.domain.entities.SuperCharges
import com.example.jucrchallenge.ui.theme.GrayLight

fun LazyListScope.nearbyPointsList(item: List<SuperCharges>) {
    items(item) {
        Card(
            shape = RoundedCornerShape(0),
            elevation = 0.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .padding(bottom = 8.dp),
            border = BorderStroke(5.dp, Color.White),
            backgroundColor = GrayLight
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column() {
                    Text(
                        text = it.address,
                        color = Color.Black,
                        style = MaterialTheme.typography.h3,
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        modifier = Modifier.alpha(0.6f),
                        text = stringResource(R.string.available,it.total,it.total),
                        color = Color.Black,
                        style = MaterialTheme.typography.caption,
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
                        .alpha(0.4f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = if(it.kms > 1.5f) painterResource(R.drawable.ic_location_lightning) else painterResource(R.drawable.ic_location_haert),
                        modifier = Modifier
                            .size((30.dp))
                            .padding(2.dp),
                        contentDescription = "My Image",
                    )
                    Text(
                        text = stringResource(R.string.kms,it.kms),
                        color = Color.Black,
                        style = MaterialTheme.typography.caption,
                    )
                }
            }
        }
    }
}