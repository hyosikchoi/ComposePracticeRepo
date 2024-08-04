package com.hyosik.composepractice.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.hyosik.composepractice.ui.theme.ComposePracticeTheme

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

                    val colorWhenScrolled = Color.Red
                    val colorWhenNotScrolled = Color.Blue

                    val scrollPosition = scrollState.value
                    val threshold = with(LocalDensity.current) { 200.dp.toPx() } // Example threshold value

                    // Determine status bar color based on scroll position
                    val statusBarColor by remember(scrollPosition) {
                        mutableStateOf(
                            if (scrollPosition > threshold) colorWhenScrolled else colorWhenNotScrolled
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
                        Spacer(modifier = Modifier.height(400.dp)) // Placeholder for content
                        Text("Coordinator 테스트 ", modifier = Modifier.padding(16.dp))
                        Spacer(modifier = Modifier.height(1000.dp)) // Placeholder for more content
                    }

                }
            }

        }
    }

}