package com.example.jucrchallenge.ui.router

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jucrchallenge.ui.main.MainModel
import com.example.jucrchallenge.ui.screens.home.HomeScreen
import com.example.jucrchallenge.ui.screens.home.HomeScreenViewModel

@Composable
fun Router(mainModel: MainModel) {

    val navController = rememberNavController()
    val homeModel = hiltViewModel<HomeScreenViewModel>()
    NavHost(
        navController = navController,
        startDestination = RouterDir.HOME.route
    ) {
        composable(RouterDir.HOME.route) {
            HomeScreen(homeModel,mainModel)
        }
    }
}