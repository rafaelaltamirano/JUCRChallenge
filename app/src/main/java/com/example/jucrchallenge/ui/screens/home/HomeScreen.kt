package com.example.jucrchallenge.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.*
import com.example.jucrchallenge.R
import com.example.jucrchallenge.ui.theme.Primary

enum class SwipingStates {//our own enum class for stoppages e.g. expanded and collapsed
EXPANDED,
    COLLAPSED
}


@OptIn(ExperimentalMaterialApi::class, ExperimentalMotionApi::class)
@Composable
fun HomeScreen (){
    val context = LocalContext.current
    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }

        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val swipingState = rememberSwipeableState(initialValue = SwipingStates.EXPANDED)
            BoxWithConstraints(//to get the max height
                modifier = Modifier.fillMaxSize()
            ) {
                val heightInPx = with(LocalDensity.current) { maxHeight.toPx() }
                val nestedScrollConnection = remember {
                    object : NestedScrollConnection {
                        override fun onPreScroll(
                            available: Offset,
                            source: NestedScrollSource
                        ): Offset {
                            val delta = available.y
                            return if (delta < 0) {
                                swipingState.performDrag(delta).toOffset()
                            } else {
                                Offset.Zero
                            }
                        }

                        override fun onPostScroll(
                            consumed: Offset,
                            available: Offset,
                            source: NestedScrollSource
                        ): Offset {
                            val delta = available.y
                            return swipingState.performDrag(delta).toOffset()
                        }

                        override suspend fun onPostFling(
                            consumed: Velocity,
                            available: Velocity
                        ): Velocity {
                            swipingState.performFling(velocity = available.y)
                            return super.onPostFling(consumed, available)
                        }

                        private fun Float.toOffset() = Offset(0f, this)
                    }
                }

                Box(//root container
                    modifier = Modifier
                        .fillMaxSize()
                        .swipeable(
                            state = swipingState,
                            thresholds = { _, _ ->
                                FractionalThreshold(0.05f)//it can be 0.5 in general
                            },
                            orientation = Orientation.Vertical,
                            anchors = mapOf(
                                0f to SwipingStates.COLLAPSED,//min height is collapsed
                                heightInPx to SwipingStates.EXPANDED,//max height is expanded
                            )
                        )
                        .nestedScroll(nestedScrollConnection)
                ) {
                    val computedProgress by remember {//progress value will be decided as par state
                        derivedStateOf {
                            if (swipingState.progress.to == SwipingStates.COLLAPSED)
                                swipingState.progress.fraction
                            else
                                1f - swipingState.progress.fraction
                        }
                    }
                    val startHeightNum = 300
                    MotionLayout(
                        modifier = Modifier.fillMaxSize(),
                        motionScene = MotionScene(content = motionScene),
                        progress = computedProgress,
                    ) {
                        Box(
                            modifier = Modifier
                                .layoutId("body")
                                .fillMaxWidth()
                                .background(Color.White)
                        ) {
                            //content, not necessarily scrollable or list
                            LazyColumn(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(200, key = { it }) {
                                    Box(
                                        modifier = Modifier
                                            .padding(24.dp)
                                            .fillMaxWidth()
                                            .background(
                                                Color.White,
                                                RoundedCornerShape(12.dp)
                                            )
                                            .border(
                                                BorderStroke(
                                                    2.dp,
                                                    Color.Gray
                                                ),
                                                RoundedCornerShape(12.dp)
                                            )
                                            .padding(50.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(it.toString())
                                    }
                                }
                            }
                        }

                        Box(
                            modifier = Modifier
                                .layoutId("header")
                                .fillMaxWidth()
                                .height(startHeightNum.dp)
                                .background(Primary)
                        ) {

                        }


                        Image(
                            painter = painterResource(R.drawable.tesla_x_white),
                            contentDescription = "My Image",

                            modifier = Modifier.width(250.dp).height(80.dp).layoutId("content1")

                        )


                        Text(
                            text = "TIME TO END OF CHANGE: 49 Min",
                            color = Color.White,

                            modifier = Modifier
                                .layoutId("content2")
                                .alpha(  alpha = 1f - computedProgress,)

                        )

                        Text(
                            text = "hola",
                            color = Color.White,

                            modifier = Modifier
                                .layoutId("content3")
                        )

                    }
                }
            }
        }
}