package com.example.jucrchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jucrchallenge.ui.main.MainModel
import com.example.jucrchallenge.ui.router.Router
import com.example.jucrchallenge.ui.theme.JUCRChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainComponent()
        }
    }
}

@Composable
fun MainComponent(mainModel: MainModel = viewModel()) {
    JUCRChallengeTheme {
        Surface(color = Color.White) {
            Router(mainModel)
        }
    }
}