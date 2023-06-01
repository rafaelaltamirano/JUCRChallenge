package com.example.jucrchallenge.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun LazyItemsRow() {

    LazyRow(
       horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(200, key = { it }) {
            StaticItem(it)
        }
    }
}