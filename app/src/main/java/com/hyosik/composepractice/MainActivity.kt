package com.hyosik.composepractice

import android.os.Build
import android.os.Bundle
import android.text.TextPaint
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.drawText
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hyosik.composepractice.ui.theme.ComposePracticeTheme
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        var isShow by rememberSaveable {
                            mutableStateOf(false)
                        }

                        Button(onClick = { isShow = !isShow }) {
                            Text(text = "다이얼로그 클릭")
                        }

                        if(isShow){
                            Box(
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(120.dp),
                                contentAlignment = Alignment.Center

                            ) {
                                Canvas(modifier = Modifier.fillMaxSize()) {
                                    val textPaint = android.graphics.Paint().apply {
                                        textSize = 100f
                                        color = android.graphics.Color.YELLOW
                                        isAntiAlias = true
                                        blendMode = android.graphics.BlendMode.LUMINOSITY
                                    }

                                    drawIntoCanvas { canvas ->
                                        // 텍스트를 그립니다
                                        canvas.nativeCanvas.drawText(
                                            "Hello World",
                                            0f,
                                            this.size.height / 2,
                                            textPaint
                                        )

                                        // BlendMode가 적용된 사각형을 그립니다

                                        drawRect(
                                            color = Color.Red,
                                            topLeft = Offset.Zero,
                                            size = size,
                                            blendMode = BlendMode.Luminosity,
                                            alpha = 0.3f
                                        )
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposePracticeTheme {
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(120.dp)
                .background(Color.Black.copy(alpha = 0.7f)),
            contentAlignment = Alignment.Center

        ) {
            Card(
                shape = RoundedCornerShape(3.dp)
            ) {
                Column {
                    Text(text = "Hello World")
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Test")
                }
            }
        }


    }
}