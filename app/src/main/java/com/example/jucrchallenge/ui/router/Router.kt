package com.example.jucrchallenge.ui.router

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jucrchallenge.ui.screens.home.HomeScreen

@Composable
fun Router() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RouterDir.HOME.route
    ) {
        composable(RouterDir.HOME.route) {
            HomeScreen()
        }
    }
}