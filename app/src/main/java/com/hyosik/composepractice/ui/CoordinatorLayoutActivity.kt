package com.hyosik.composepractice.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.hyosik.composepractice.R
import com.hyosik.composepractice.ui.theme.ComposePracticeTheme
import kotlin.math.roundToInt

class CoordinatorLayoutActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracticeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val scrollState = rememberScrollState()

                    val colorWhenScrolled = Color.White
                    val colorWhenNotScrolled = Color.Black
//
                    var imageHeight by remember { mutableIntStateOf(0) }
//
//                    // Determine status bar color based on scroll position
                    //TODO 리컴포지션의 원인 제공... remember 의 값이 바뀌면 동일 선상의 컴포저블들이 전부 리컴포지션 되는듯...
//                    val statusBarColor by remember(scrollState.value) {
//                        mutableStateOf(
//                            if (scrollState.value > imageHeight) colorWhenScrolled else colorWhenNotScrolled
//                        )
//                    }
//                  //TODO 추가적으로 key1 의 값이 변경 될 때마다 동일 선상의 컴포저블들 리컴포지션 됨.
                    LaunchedEffect(key1 = scrollState.value) {
//                        this@CoordinatorLayoutActivity.window.statusBarColor = if (scrollState.value > imageHeight) colorWhenScrolled.toArgb() else colorWhenNotScrolled.toArgb() // statusBar 색상 설정
//                        WindowCompat.getInsetsController(this@CoordinatorLayoutActivity.window, this@CoordinatorLayoutActivity.window.decorView).apply {
//                            isAppearanceLightStatusBars =
//                                scrollState.value > imageHeight // statusBar 상단 아이콘 light, dark 설정
//                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState)
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .onGloballyPositioned { coordinates ->
                                    val imageSize = coordinates.size
                                    if (imageHeight != imageSize.height) {
                                        imageHeight = imageSize.height
                                    }
                                }
                            ,
                            painter = painterResource(id = R.drawable.cat),
                            contentDescription = "고양이 사진",
                            contentScale = ContentScale.FillWidth
                        )
                        Spacer(modifier = Modifier.height(400.dp)) // Placeholder for content
                        Text("Coordinator 테스트 ", modifier = Modifier.padding(16.dp))
                        Spacer(modifier = Modifier.height(1000.dp)) // Placeholder for more content
                    }

                }
            }

        }
    }

}