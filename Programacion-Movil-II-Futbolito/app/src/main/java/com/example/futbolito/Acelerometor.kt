package com.example.futbolito

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import dev.ricknout.composesensors.accelerometer.isAccelerometerSensorAvailable
import dev.ricknout.composesensors.accelerometer.rememberAccelerometerSensorValueAsState


data class Obstacle(val position: Offset, val size: Size)

@Composable
fun AccelerometerDemo() {
    if (isAccelerometerSensorAvailable()) {
        val sensorValue by rememberAccelerometerSensorValueAsState()
        val (x, y, z) = sensorValue.value

        // Define las dimensiones del campo
        val fieldWidth = 600f
        val fieldHeight = 1200f

        // Define las dimensiones de las porterías (obstáculos)
        val goalWidth = 200f
        val goalHeight = 100f
        val topGoalY = 0f
        val bottomGoalY = fieldHeight - goalHeight
        val goalX = (fieldWidth - goalWidth) / 2

        // Lista de obstáculos (porterías)
        val obstacles = listOf(
            Obstacle(Offset(goalX, topGoalY), Size(goalWidth, goalHeight)),
            Obstacle(Offset(goalX, bottomGoalY), Size(goalWidth, goalHeight))
        )

        // Contadores para cada portería
        var goalsLeft by remember { mutableStateOf(0) }
        var goalsRight by remember { mutableStateOf(0) }

        Demo(
            demo = Demo.ACCELEROMETER,
            value = " ",
        ) {
            val width = constraints.maxWidth.toFloat()
            val height = constraints.maxHeight.toFloat()
            var center by remember { mutableStateOf(Offset(width / 2, height / 2)) }
            val orientation = LocalConfiguration.current.orientation
            val contentColor = LocalContentColor.current
            val radius = with(LocalDensity.current) { 10.dp.toPx() }

            val collidedLeft = obstacles[0].let {
                center.x + radius > it.position.x && center.x - radius < it.position.x + it.size.width &&
                        center.y + radius > it.position.y && center.y - radius < it.position.y + it.size.height
            }

            val collidedRight = obstacles[1].let {
                center.x + radius > it.position.x && center.x - radius < it.position.x + it.size.width &&
                        center.y + radius > it.position.y && center.y - radius < it.position.y + it.size.height
            }

            if (collidedLeft) {
                center = Offset(width / 2, height / 2)
                goalsLeft++
            }

            if (collidedRight) {
                center = Offset(width / 2, height / 2)
                goalsRight++
            }

            // Actualiza el centro basado en los valores del acelerómetro
            center = if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                Offset(
                    x = (center.x - x).coerceIn(radius, fieldWidth - radius),
                    y = (center.y + y).coerceIn(radius, fieldHeight - radius),
                )
            } else {
                Offset(
                    x = (center.x + y).coerceIn(radius, fieldWidth - radius),
                    y = (center.y + x).coerceIn(radius, fieldHeight - radius),
                )
            }

            Box {
                // val image = ImageBitmap.imageResource(id = R.drawable.futbolito)
                Canvas(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Dibuja el campo de fútbol
                    drawRect(color = Color.Blue, size = Size(fieldWidth, fieldHeight))
                    /*drawImage(
                        image = image,
                        dstSize = IntSize(width.toInt(), height.toInt())
                    )*/
                    // Dibuja los obstáculos (porterías)
                    for (obstacle in obstacles) {
                        drawRect(
                            color = Color.Red,
                            topLeft = obstacle.position,
                            size = obstacle.size
                        )
                    }

                    // Dibuja el círculo
                    drawCircle(
                        color = if (collidedLeft || collidedRight) Color.White else contentColor,
                        radius = radius,
                        center = center,
                    )
                }

                Column(modifier = Modifier.align(Alignment.Center)){
                    Text(text = goalsLeft.toString())
                    Text(text = goalsRight.toString())
                }
            }
        }
    } else {
        NotAvailableDemo(demo = Demo.ACCELEROMETER)
    }
}