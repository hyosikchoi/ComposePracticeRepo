package com.hyosik.composepractice

import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.text.TextPaint
import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.hyosik.composepractice.ui.BottomSheet
import com.hyosik.composepractice.ui.CustomDialog
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 시스템 바(예: 상태 바, 네비게이션 바) 뒤에 콘텐츠를 그릴 수 있게 하는 데 사용됩니다.
        WindowCompat.setDecorFitsSystemWindows(window, false)

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

                        var isShowDialog by rememberSaveable {
                            mutableStateOf(false)
                        }

                        Button(onClick = { isShow = !isShow }) {
                            Text(text = "바텀시트 오픈")
                        }

                        Button(onClick = { isShowDialog = !isShowDialog }) {
                            Text(text = "다이얼로그 오픈")
                        }

//                        if(isShow){
//                            Box(
//                                modifier = Modifier
//                                    .width(200.dp)
//                                    .height(120.dp)
//                                    .clip(RoundedCornerShape(10.dp))
//                                    .drawWithContent {
//                                        drawContent()
//                                        drawRect(
//                                            color = Color.Red,
//                                            blendMode = BlendMode.Luminosity,
//                                            alpha = 0.3f,
//                                        )
//                                    }
//                                ,
//                                contentAlignment = Alignment.Center
//
//                            ) {
//
//                                Canvas(modifier = Modifier.fillMaxSize()) {
//
//                                    val textPaint = android.graphics.Paint().apply {
//                                        textSize = 100f
//                                        color = android.graphics.Color.RED
//                                        isAntiAlias = true
//                                        blendMode = android.graphics.BlendMode.LUMINOSITY
//                                        alpha = 85 // 30%
//                                    }
//
//                                    drawIntoCanvas { canvas ->
//                                        // 텍스트를 그립니다
//                                        canvas.nativeCanvas.drawText(
//                                            "Hello World",
//                                            this.size.width * 0.05f,
//                                            this.size.height * 0.55f,
//                                            textPaint,
//                                        )
//
//                                        // BlendMode가 적용된 사각형을 그립니다
//
////                                        drawRect(
////                                            color = Color.Red,
////                                            topLeft = Offset.Zero,
////                                            size = size,
////                                            blendMode = BlendMode.Luminosity,
////                                            alpha = 0.3f
////                                        )
//                                    }
//                                }
//                            }
//                        }

                        var timer by rememberSaveable {
                            mutableLongStateOf(30L)
                        }


                        if (isShow) {

                            val countDownTimer = object : CountDownTimer(30000, 1000) {

                                override fun onTick(time: Long) {
                                    timer = time / 1000L
                                }

                                override fun onFinish() {
                                    start()
                                }
                            }

                            countDownTimer.start()

                            BottomSheet(
                                dragBackgroundColor = Color.Blue,
                                content = {
                                    Column(
                                        modifier = Modifier
                                            .background(color = Color.Blue)
                                            .fillMaxWidth()
                                            .wrapContentHeight(),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Column {
                                            Text(
                                                modifier = Modifier.padding(20.dp),
                                                text = "타이머 : $timer 초"
                                            )
                                        }

                                    }
                                },
                                onDismiss = {
                                    isShow = false
                                    countDownTimer.cancel()
                                }
                            )
                        }

                        if(isShowDialog) {
                            CustomDialog(onDismiss = { isShowDialog = false }) {
                                Column(
                                    modifier = Modifier
                                        .width(100.dp)
                                        .height(100.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(text = "다이얼로그")
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