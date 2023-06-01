package com.example.jucrchallenge.ui.screens.home

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.*
import com.example.jucrchallenge.R
import com.example.jucrchallenge.ui.component.AvailableSpaceView
import com.example.jucrchallenge.ui.component.LazyItemsRow
import com.example.jucrchallenge.ui.component.StatisticsTopBar
import com.example.jucrchallenge.ui.component.nearbyPointsList
import com.example.jucrchallenge.ui.theme.Primary

enum class SwipingStates {
    //our own enum class for stoppages e.g. expanded and collapsed
    EXPANDED, COLLAPSED
}


@OptIn(ExperimentalMaterialApi::class, ExperimentalMotionApi::class)
@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val realAvailable = 1f - 0.7f
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene).readBytes().decodeToString()
    }

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
    ) {
        val swipingState = rememberSwipeableState(initialValue = SwipingStates.EXPANDED)
        BoxWithConstraints(//to get the max height
            modifier = Modifier.fillMaxSize()
        ) {
            val heightInPx = with(LocalDensity.current) { maxHeight.toPx() }
            val nestedScrollConnection = remember {
                object : NestedScrollConnection {
                    override fun onPreScroll(
                        available: Offset, source: NestedScrollSource
                    ): Offset {
                        val delta = available.y
                        return if (delta < 0) {
                            swipingState.performDrag(delta).toOffset()
                        } else {
                            Offset.Zero
                        }
                    }

                    override fun onPostScroll(
                        consumed: Offset, available: Offset, source: NestedScrollSource
                    ): Offset {
                        val delta = available.y
                        return swipingState.performDrag(delta).toOffset()
                    }

                    override suspend fun onPostFling(
                        consumed: Velocity, available: Velocity
                    ): Velocity {
                        swipingState.performFling(velocity = available.y)
                        return super.onPostFling(consumed, available)
                    }

                    private fun Float.toOffset() = Offset(0f, this)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .swipeable(
                        state = swipingState, thresholds = { _, _ ->
                            FractionalThreshold(0.05f)//it can be 0.5 in general
                        }, orientation = Orientation.Vertical, anchors = mapOf(
                            0f to SwipingStates.COLLAPSED,//min height is collapsed
                            heightInPx to SwipingStates.EXPANDED,//max height is expanded
                        )
                    )
                    .nestedScroll(nestedScrollConnection)
            ) {
                val computedProgress by remember {//progress value will be decided as par state
                    derivedStateOf {
                        if (swipingState.progress.to == SwipingStates.COLLAPSED) swipingState.progress.fraction
                        else 1f - swipingState.progress.fraction
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
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                            .background(Color.White, RoundedCornerShape(12.dp))
                    ) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(1) {StatisticsTopBar("Statistics")}
                            item{  LazyItemsRow()}
                            items(1) {
                                Spacer(Modifier.height(24.dp))
                                StatisticsTopBar("Nearby supercharges","View all")
                                Spacer(Modifier.height(12.dp))
                            }
                            nearbyPointsList()

                        }
                    }

                    Box(
                        modifier = Modifier
                            .layoutId("header")
                            .fillMaxWidth()
                            .height(startHeightNum.dp)
                            .background(Primary)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .layoutId("canvas")
                    ) {
                        Canvas(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(30.dp)
                        ) {
                            val canvasWidth = size.width
                            val canvasHeight = size.height
                            val formWidth = (canvasWidth * 2)
                            val xPos = canvasWidth / 2

                            drawArc(
                                Color.White,
                                -180f,
                                180f,
                                useCenter = false,
                                size = Size(formWidth, canvasHeight * 4),
                                topLeft = Offset(x = -xPos, y = canvasHeight - 150)
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .layoutId("circle"),
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Card(
                            shape = RoundedCornerShape(100),
                            modifier = Modifier
                                .size(72.dp)
                                .clip(CircleShape),
                            border = BorderStroke(5.dp, Color.White),
                            backgroundColor = Color.White
                        ) {
                            AndroidView(
                                factory = { context ->
                                    AvailableSpaceView(context).apply {
                                        setAvailable(realAvailable)
                                    }
                                }, modifier = Modifier
                                    .fillMaxWidth()
                                    .height(128.dp)
                            )
                        }
                    }
                    Text(
                        text = "Good Morning Billy",
                        color = Color.White,
                        modifier = Modifier
                            .layoutId("welcome_message")
                            .alpha(alpha = 1f - computedProgress)

                    )
                    Text(
                        text = "Charging your car...",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .layoutId("charging_message")
                            .alpha(alpha = 1f - computedProgress)

                    )
                    Image(
                        painter = painterResource(R.drawable.tesla_x_white),
                        contentDescription = "My Image",

                        modifier = Modifier
                            .width(250.dp)
                            .height(80.dp)
                            .layoutId("content1")

                    )


                    Text(
                        text = "TIME TO END OF CHANGE: 49 Min",
                        color = Color.White,
                        modifier = Modifier
                            .layoutId("time_charge")
                            .alpha(alpha = 1f - computedProgress)

                    )


                    Column(
                        modifier = Modifier.layoutId("content3")
                    ) {
                        Text(
                            text = "Tesla Model X",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )
                        Text(
                            text = "49 Min to End",
                            color = Color.White,

                            )
                    }


                }
            }
        }
    }
}