package com.sz.handwriting.ui.widget

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Paint
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.sz.handwriting.BitmapUtils
import com.sz.handwriting.SpringBoardConfig.ITEM_SIZE
import com.sz.handwriting.viewmodel.SpringBoardViewModel

@Composable
fun SpringPreviewPage(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AppToolsBar(title = "长按保存春联", onBack = {
            onBack.invoke()
        })
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpringPreview()
        }
    }

}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun SpringPreview(viewModel: SpringBoardViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val states by viewModel.viewStates.collectAsState()
    val itemSize = with(LocalDensity.current) { ITEM_SIZE.dp.toPx() }
    val bitmap = remember {
        Bitmap.createBitmap(
            itemSize.toInt(), (states.bitmapList.size * itemSize).toInt(), Bitmap.Config.ARGB_8888
        )
    }
    val newCanvas = remember { android.graphics.Canvas(bitmap) }
    val paint = remember { Paint() }
    val context = LocalContext.current
    BoxWithConstraints(
        modifier = Modifier
            .width(ITEM_SIZE.dp)
            .height((ITEM_SIZE * states.bitmapList.size).dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        BitmapUtils.saveBitmapToGallery(context, bitmap, "春联")
                        Toast
                            .makeText(context, "对联已保存", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
            }
    ) {
        newCanvas.drawColor(android.graphics.Color.RED)
        for (i in states.bitmapList.indices) {
            newCanvas.drawBitmap(states.bitmapList[i], 0f, itemSize * i, paint)
        }

        Canvas(modifier = Modifier.fillMaxSize()) {
            drawImage(bitmap.asImageBitmap(), Offset.Zero)
        }
    }
}