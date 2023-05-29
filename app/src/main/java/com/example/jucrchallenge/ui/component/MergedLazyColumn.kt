package com.example.jucrchallenge.ui.component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun MergedLazyColumn() {

    LazyRow(
       horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(200, key = { it }) {
            StaticItem(it)
        }
    }
}