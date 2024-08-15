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

                    // Determine status bar color based on scroll position
                    var isStatusBarColorChanged by remember {
                        mutableStateOf(false)
                    }
                    /** 리팩토링 성공!
                     *  최대한 key 값이 여러번 바뀌지 않게끔 설계를 하자!
                     *  key 값이 변경 될 때마다 동일 계층의 컴포넌트들이 리컴포지션 대상자가 된다는걸 잊지말자!
                     */
                    LaunchedEffect(key1 = isStatusBarColorChanged) {
                        this@CoordinatorLayoutActivity.window.statusBarColor = if (isStatusBarColorChanged) colorWhenScrolled.toArgb() else colorWhenNotScrolled.toArgb() // statusBar 색상 설정
                        WindowCompat.getInsetsController(this@CoordinatorLayoutActivity.window, this@CoordinatorLayoutActivity.window.decorView).apply {
                            isAppearanceLightStatusBars =
                                isStatusBarColorChanged // statusBar 상단 아이콘 light, dark 설정
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
                                    if (scrollState.value > imageSize.height && isStatusBarColorChanged.not()) {
                                        isStatusBarColorChanged = true
                                    } else if (scrollState.value <= imageSize.height && isStatusBarColorChanged) {
                                        isStatusBarColorChanged = false
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