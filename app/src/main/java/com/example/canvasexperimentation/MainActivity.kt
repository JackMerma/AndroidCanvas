package com.example.canvasexperimentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.example.canvasexperimentation.ui.theme.CanvasExperimentationTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCanvas()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyCanvas() {
    // Circle position
    var circlePosition by remember { mutableStateOf(generateRandomPosition(300f, 300.0f, 10f)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        // Canvas definition
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = Dp(40.0f), horizontal = Dp(10f))
        ) {
            val strokeWidth = Dp(2.0f).toPx()
            val radius = Dp(10.0f).toPx()

            // Generating random coordinates
            val x = Random.nextFloat() * (size.width - 2 * radius) + radius
            val y = Random.nextFloat() * (size.height - 2 * radius) + radius

            // Drawing the rectangle
            drawRect(
                color = Color.Blue,
                size = size,
                style = Stroke(width = strokeWidth)
            )

            // Drawing the circle
            drawCircle(
                color = Color.Green,
                radius = radius,
                center = androidx.compose.ui.geometry.Offset(x, y)
            )
        }

        // Button to update the canvas
        Button(
            onClick = {
                circlePosition = generateRandomPosition(100f, 100f, 10f)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Update Circle Position")
        }
    }
}

// Function to generate a random position
fun generateRandomPosition(canvasWidth: Float, canvasHeight: Float, radius: Float): androidx.compose.ui.geometry.Offset {
    val x = Random.nextFloat() * (canvasWidth - 2 * radius) + radius
    val y = Random.nextFloat() * (canvasHeight - 2 * radius) + radius
    return androidx.compose.ui.geometry.Offset(x, y)
}