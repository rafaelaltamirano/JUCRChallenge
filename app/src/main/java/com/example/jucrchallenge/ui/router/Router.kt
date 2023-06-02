package com.example.jucrchallenge.ui.router

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jucrchallenge.ui.component.DefineBottomNav
import com.example.jucrchallenge.ui.main.MainModel
import com.example.jucrchallenge.ui.map.MapScreen
import com.example.jucrchallenge.ui.profile.ProfileScreen
import com.example.jucrchallenge.ui.router.RouterDir.*
import com.example.jucrchallenge.ui.screens.home.HomeScreen
import com.example.jucrchallenge.ui.screens.home.HomeScreenViewModel
import com.example.jucrchallenge.ui.search.SearchScreen
import com.example.jucrchallenge.ui.splash.SplashScreen

@Composable
fun Router(mainModel: MainModel) {

    val navController = rememberNavController()
    val homeModel = hiltViewModel<HomeScreenViewModel>()
    val scaffoldState = rememberScaffoldState()


    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            if (!mainModel.state.showSplash){
                DefineBottomNav(
                    navController,
                )
            }
        },
    ) {
        NavHost(
            navController = navController,
            startDestination = if (mainModel.state.showSplash) SPLASH.route else HOME.route,
            modifier = Modifier.padding(bottom =  it.calculateBottomPadding())
        ) {
            composable(SPLASH.route) { SplashScreen() }
            composable(HOME.route) {
                HomeScreen(homeModel, mainModel)
            }
            composable(SCREEN_1.route) {
                SearchScreen()
            }
            composable(SCREEN_2.route) {
                MapScreen()
            }
            composable(SCREEN_3.route) {
                ProfileScreen()
            }
        }
    }

}
