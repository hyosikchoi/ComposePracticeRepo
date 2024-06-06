package com.hyosik.composepractice.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog


@Composable
fun CustomDialog(
    onDismiss: () -> Unit,
    components: @Composable () -> Unit
) {

    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = RoundedCornerShape(6.dp)
        ) {
            components()
        }
    }

}