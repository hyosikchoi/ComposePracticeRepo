package com.hyosik.composepractice.ui

import android.os.Bundle
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
                    //TODO 스크롤시 계속 리컴포지션 되는거 잡기
                    val scrollState = rememberScrollState()

                    val colorWhenScrolled = Color.White
                    val colorWhenNotScrolled = Color.Black

                    val scrollPosition = scrollState.value
                    val threshold = with(LocalDensity.current) { 200.dp.toPx() } // Example threshold value
                    var imageHeight by remember { mutableStateOf(0) }

                    // Determine status bar color based on scroll position
                    val statusBarColor by remember(scrollPosition) {
                        mutableStateOf(
                            if (scrollPosition > imageHeight) colorWhenScrolled else colorWhenNotScrolled
                        )
                    }
                    
                    LaunchedEffect(key1 = statusBarColor) {
                        this@CoordinatorLayoutActivity.window.statusBarColor = statusBarColor.toArgb() // statusBar 색상 설정
                        WindowCompat.getInsetsController(this@CoordinatorLayoutActivity.window, this@CoordinatorLayoutActivity.window.decorView).apply {
                            isAppearanceLightStatusBars =
                                statusBarColor == colorWhenScrolled // statusBar 상단 아이콘 light, dark 설정
                        }
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
                                    if(imageHeight != imageSize.height) imageHeight = imageSize.height
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