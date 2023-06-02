package com.example.jucrchallenge.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jucrchallenge.R
import com.example.jucrchallenge.ui.router.RouterDir.*
import com.example.jucrchallenge.ui.theme.Primary


@Composable
fun DefineBottomNav(navController: NavController) {
    val items = listOf(
        HOME, SCREEN_1, SCREEN_2, SCREEN_3
    )

    var value by remember { mutableStateOf(30f) }
    Slider(value = value, onValueChange = { value = it }, valueRange = 0f..100f)
            BottomNavigation(modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .graphicsLayer {
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                    clip = true
                    shadowElevation = value
                }) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { item ->
                    BottomNavigationItem(icon = {
                        Icon(
                            painterResource(id = item.icon),
                            contentDescription = item.title,
                            modifier = Modifier.padding(24.dp)
                        )
                    },
                        selectedContentColor = Primary,
                        unselectedContentColor = Color.Black.copy(0.4f),
                        alwaysShowLabel = true,
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {

                                navController.graph.startDestinationRoute?.let { screen_route ->
                                    popUpTo(screen_route) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        })
                }
            }

}
