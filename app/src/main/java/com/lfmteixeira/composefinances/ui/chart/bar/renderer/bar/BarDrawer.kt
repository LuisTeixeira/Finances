package com.lfmteixeira.composefinances.ui.chart.bar.renderer.bar

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.lfmteixeira.composefinances.ui.chart.bar.BarChartData

interface BarDrawer {
    fun drawBar(
        drawScope: DrawScope,
        canvas: Canvas,
        barArea: Rect,
        bar: BarChartData.Bar
    )
}