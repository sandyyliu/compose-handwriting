package com.sz.handwriting.ui.widget

import androidx.compose.runtime.*


@Composable
fun HomeEntry() {
    var isPreview by remember { mutableStateOf(false) }

    if (isPreview) {
        SpringPreviewPage { isPreview = false }
    } else {
        SpringPage { isPreview = true }
    }

}

